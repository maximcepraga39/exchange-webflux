package md.orange.exchangeapp.service;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.dto.CashRqDto;
import md.orange.exchangeapp.entity.Numerar;
import md.orange.exchangeapp.entity.NumerarWithDictionarValuteAndCasaDeSchimb;
import md.orange.exchangeapp.repository.CasaDeSchimbRepository;
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
    private final CasaDeSchimbRepository casaDeSchimbRepository;

    public Flux<NumerarWithDictionarValuteAndCasaDeSchimb> getAllCash() {
        return numerarRepository.findAll()
                .flatMap(combineNumerarWithDictionarValuteAndCasaDeSchimb());
    }

    public Mono<NumerarWithDictionarValuteAndCasaDeSchimb> getCashByCurrencyCodeAndCasaDeSchimb(String currencyCode, String numeCasaDeSchimb) {
        return numerarRepository.findByCodValutaAndNumeCasaDeSchimb(currencyCode, numeCasaDeSchimb)
                .flatMap(combineNumerarWithDictionarValuteAndCasaDeSchimb());
    }


    public Mono<NumerarWithDictionarValuteAndCasaDeSchimb> addCash(Numerar numerar) {
        return numerarRepository.save(numerar)
                .flatMap(combineNumerarWithDictionarValuteAndCasaDeSchimb());
    }

    public Mono<Integer> updateCash(CashRqDto cashRequest) {
        return numerarRepository.updateNumerarByCodValutaAndNumeCasaDeSchimb(cashRequest.getCodValuta(), cashRequest.getNumeCasaDeSchimb(), cashRequest.getSuma());
    }

    public Mono<Integer> deleteCash(String codValuta, String numeCasaDeSchimb) {
        return numerarRepository.deleteNumerarByCodValuta(codValuta, numeCasaDeSchimb);
    }

    private Function<Numerar, Mono<NumerarWithDictionarValuteAndCasaDeSchimb>> combineNumerarWithDictionarValuteAndCasaDeSchimb() {
        return savedNumerar -> currencyService.getCurrencyById(savedNumerar.getValutaId())
                .flatMap(dictionarValute -> {
                    var numerarWithDictionarValuteAndCasaDeSchimb = new NumerarWithDictionarValuteAndCasaDeSchimb();
                    numerarWithDictionarValuteAndCasaDeSchimb.setNumerar(savedNumerar);
                    numerarWithDictionarValuteAndCasaDeSchimb.setDictionarValute(dictionarValute);

                    return casaDeSchimbRepository.findById(savedNumerar.getCasaDeSchimbId())
                            .map(casaDeSchimb -> {
                                numerarWithDictionarValuteAndCasaDeSchimb.setCasaDeSchimb(casaDeSchimb);
                                return numerarWithDictionarValuteAndCasaDeSchimb;
                            });
                });
    }

}
