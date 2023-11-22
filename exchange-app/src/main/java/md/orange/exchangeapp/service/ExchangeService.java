package md.orange.exchangeapp.service;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.dto.ExchangeRqDto;
import md.orange.exchangeapp.dto.ExchangeRsDto;
import md.orange.exchangeapp.entity.SchimbValutar;
import md.orange.exchangeapp.enums.CurrencyCode;
import md.orange.exchangeapp.exception.CurrencyNotFoundException;
import md.orange.exchangeapp.exception.NotEnoughMoneyException;
import md.orange.exchangeapp.repository.DictionarValuteRepository;
import md.orange.exchangeapp.repository.NumerarRepository;
import md.orange.exchangeapp.repository.SchimbValutarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final NumerarRepository numerarRepository;
    private final SchimbValutarRepository schimbValutarRepository;
    private final DictionarValuteRepository dictionarValuteRepository;
    private final RateService rateService;

    @Transactional
    public Mono<ExchangeRsDto> exchange(ExchangeRqDto exchangeRequest) {
        return numerarRepository.findByCodValuta(exchangeRequest.getCodValuta())
                .filter(numerar -> numerar.getSuma() >= exchangeRequest.getSuma())
                .switchIfEmpty(Mono.error(new NotEnoughMoneyException()))
                .flatMap(numerar -> {
                    numerar.setSuma(numerar.getSuma() - exchangeRequest.getSuma());
                    return numerarRepository.updateNumerarById(numerar.getId(), numerar.getSuma());
                })
                .then(numerarRepository.findByCodValuta(CurrencyCode.MDL.name())
                        .switchIfEmpty(Mono.error(new CurrencyNotFoundException(CurrencyCode.MDL.name())))
                        .flatMap(numerar -> rateService.getCursValutarByCurrencyCodeForToday(exchangeRequest.getCodValuta())
                                .flatMap(cursValutarWithDictionarValute -> {
                                    var curs = cursValutarWithDictionarValute.getCursValutar().getCurs();
                                    numerar.setSuma(numerar.getSuma() + curs * exchangeRequest.getSuma());
                                    return numerarRepository.updateNumerarById(numerar.getId(), numerar.getSuma());
                                })))
                .then(dictionarValuteRepository.findByCodValuta(exchangeRequest.getCodValuta())
                        .switchIfEmpty(Mono.error(new CurrencyNotFoundException(exchangeRequest.getCodValuta())))
                        .flatMap(dictionarValute -> rateService.getCursValutarByCurrencyCodeForToday(exchangeRequest.getCodValuta())
                                .flatMap(cursValutarWithDictionarValute -> {
                                    var curs = cursValutarWithDictionarValute.getCursValutar();
                                    var schimbValutar = new SchimbValutar();
                                    schimbValutar.setValutaId(dictionarValute.getId());
                                    schimbValutar.setCursValutarId(curs.getId());
                                    schimbValutar.setSumaPrimita(exchangeRequest.getSuma());
                                    schimbValutar.setSumaEliberata(curs.getCurs() * exchangeRequest.getSuma());
                                    schimbValutar.setUtilizator(exchangeRequest.getUtilizator());
                                    return schimbValutarRepository.save(schimbValutar);
                                })))
                .map(schimbValutar -> ExchangeRsDto.builder()
                        .codValuta(exchangeRequest.getCodValuta())
                        .sumaPrimita(exchangeRequest.getSuma())
                        .sumaEliberata(schimbValutar.getSumaEliberata())
                        .utilizator(exchangeRequest.getUtilizator())
                        .build());
    }
}
