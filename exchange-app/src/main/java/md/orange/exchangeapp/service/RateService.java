package md.orange.exchangeapp.service;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.entity.CursValutar;
import md.orange.exchangeapp.entity.CursValutarWithDictionarValute;
import md.orange.exchangeapp.repository.CursValutarRepository;
import md.orange.exchangeapp.repository.DictionarValuteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RateService {
    private final CursValutarRepository cursValutarRepository;
    private final DictionarValuteRepository dictionarValuteRepository;

    public Mono<CursValutarWithDictionarValute> saveCursValutar(Mono<CursValutar> cursValutar) {
        return cursValutar.flatMap(cursValutarRepository::save)
                .flatMap(savedCursValutar -> dictionarValuteRepository
                        .findById(savedCursValutar.getValutaId())
                        .map(dictionarValute -> {
                            var cursValutarWithDictionarValute = new CursValutarWithDictionarValute();
                            cursValutarWithDictionarValute.setCursValutar(savedCursValutar);
                            cursValutarWithDictionarValute.setDictionarValute(dictionarValute);

                            return cursValutarWithDictionarValute;
                        }));
    }

    public Flux<CursValutarWithDictionarValute> getCursValutar() {
//        return cursValutarRepository.findAll()
//                .flatMap(cursValutar -> {
//                    var dictionarValute = dictionarValuteRepository
//                            .findById(cursValutar.getValutaId())
//                            .block();
//
//                    var cursValutarWithDictionarValute = new CursValutarWithDictionarValute();
//                    cursValutarWithDictionarValute.setCursValutar(cursValutar);
//                    cursValutarWithDictionarValute.setDictionarValute(dictionarValute);
//
//                    return cursValutarWithDictionarValute;
//                });
        return cursValutarRepository.findAll()
                .flatMap(cursValutar -> dictionarValuteRepository
                        .findById(cursValutar.getValutaId())
                        .map(dictionarValute -> {
                            var cursValutarWithDictionarValute = new CursValutarWithDictionarValute();
                            cursValutarWithDictionarValute.setCursValutar(cursValutar);
                            cursValutarWithDictionarValute.setDictionarValute(dictionarValute);

                            return cursValutarWithDictionarValute;
                        }));
    }

    public Mono<CursValutarWithDictionarValute> getCursValutarByCurrencyCode(String currencyCode) {
        return cursValutarRepository.findByCodValuta(currencyCode)
                .flatMap(cursValutar -> dictionarValuteRepository
                        .findById(cursValutar.getValutaId())
                        .map(dictionarValute -> {
                            var cursValutarWithDictionarValute = new CursValutarWithDictionarValute();
                            cursValutarWithDictionarValute.setCursValutar(cursValutar);
                            cursValutarWithDictionarValute.setDictionarValute(dictionarValute);

                            return cursValutarWithDictionarValute;
                        }));
    }
}
