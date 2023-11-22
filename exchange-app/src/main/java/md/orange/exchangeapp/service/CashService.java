package md.orange.exchangeapp.service;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.dto.CashRqDto;
import md.orange.exchangeapp.dto.DeleteCacheRqDto;
import md.orange.exchangeapp.entity.Numerar;
import md.orange.exchangeapp.entity.NumerarWithDictionarValute;
import md.orange.exchangeapp.repository.NumerarRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CashService {
    private final NumerarRepository numerarRepository;
    private final CurrencyService currencyService;

    public Flux<NumerarWithDictionarValute> getAllCash() {
        return numerarRepository.findAll()
                .flatMap(combineNumerarWithDictionarValute());
    }

    public Mono<NumerarWithDictionarValute> getCashByCurrencyCode(String currencyCode) {
        return numerarRepository.findByCodValuta(currencyCode)
                .flatMap(combineNumerarWithDictionarValute());
    }


    public Mono<NumerarWithDictionarValute> addCash(Numerar numerar) {
        return numerarRepository.save(numerar)
                .flatMap(combineNumerarWithDictionarValute());
    }

    public Mono<NumerarWithDictionarValute> updateCash(CashRqDto cashRequest) {
        return numerarRepository.updateNumerarByCodValuta(cashRequest.getCodValuta(), cashRequest.getSuma())
                .flatMap(combineNumerarWithDictionarValute());
    }

    public Mono<NumerarWithDictionarValute> deleteCash(String codValuta) {
        return numerarRepository.deleteNumerarByCodValuta(codValuta)
                .flatMap(combineNumerarWithDictionarValute());
    }
    private Function<Numerar, Mono<? extends NumerarWithDictionarValute>> combineNumerarWithDictionarValute() {
        return savedNumerar -> currencyService.getCurrencyById(savedNumerar.getValutaId())
                .map(dictionarValute -> {
                    var numerarWithDictionarValute = new NumerarWithDictionarValute();
                    numerarWithDictionarValute.setNumerar(savedNumerar);
                    numerarWithDictionarValute.setDictionarValute(dictionarValute);

                    return numerarWithDictionarValute;
                });
    }
}
