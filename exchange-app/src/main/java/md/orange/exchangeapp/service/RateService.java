package md.orange.exchangeapp.service;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.entity.CursValutar;
import md.orange.exchangeapp.entity.CursValutarWithDictionarValute;
import md.orange.exchangeapp.repository.CursValutarRepository;
import md.orange.exchangeapp.repository.DictionarValuteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RateService {
    private final CursValutarRepository cursValutarRepository;
    private final DictionarValuteRepository dictionarValuteRepository;

    public Mono<CursValutarWithDictionarValute> saveCursValutar(CursValutar cursValutar) {
        return cursValutarRepository.save(cursValutar)
                .flatMap(savedCursValutar -> dictionarValuteRepository
                        .findById(savedCursValutar.getValutaId())
                        .map(dictionarValute -> {
                            var cursValutarWithDictionarValute = new CursValutarWithDictionarValute();
                            cursValutarWithDictionarValute.setCursValutar(savedCursValutar);
                            cursValutarWithDictionarValute.setDictionarValute(dictionarValute);

                            return cursValutarWithDictionarValute;
                        }));
    }

    public Flux<CursValutarWithDictionarValute> getCursValutarByDate(LocalDate date) {
        return cursValutarRepository.findAllByDataCurs(date)
                .flatMap(cursValutar -> dictionarValuteRepository
                        .findById(cursValutar.getValutaId())
                        .map(dictionarValute -> {
                            var cursValutarWithDictionarValute = new CursValutarWithDictionarValute();
                            cursValutarWithDictionarValute.setCursValutar(cursValutar);
                            cursValutarWithDictionarValute.setDictionarValute(dictionarValute);

                            return cursValutarWithDictionarValute;
                        }));
    }

    public Mono<CursValutarWithDictionarValute> getCursValutarByCurrencyCodeForToday(String currencyCode) {
        return getCursValutarByCurrencyCodeByDate(currencyCode, LocalDate.now());
    }


    public Mono<CursValutarWithDictionarValute> getCursValutarByCurrencyCodeByDate(String currencyCode, LocalDate date) {
        return cursValutarRepository.findByCodValutaAndDataCurs(currencyCode, date)
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
