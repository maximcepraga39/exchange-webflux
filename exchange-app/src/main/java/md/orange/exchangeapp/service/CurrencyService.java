package md.orange.exchangeapp.service;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.entity.DictionarValute;
import md.orange.exchangeapp.repository.DictionarValuteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final DictionarValuteRepository dictionarValuteRepository;

    public Mono<DictionarValute> addCurrency(DictionarValute dictionarValute) {
        return dictionarValuteRepository.save(dictionarValute);
    }

    public Flux<DictionarValute> getAllCurrencies() {
        return dictionarValuteRepository.findAll();
    }

    public Mono<DictionarValute> getCurrencyByCurrencyCode(String currencyCode) {
        return dictionarValuteRepository
                .findByCodValuta(currencyCode);
    }

    public Mono<DictionarValute> getCurrencyById(Long id) {
        return dictionarValuteRepository.findById(id);
    }

    public Mono<DictionarValute> deleteCurrency(String currencyCode) {
        return dictionarValuteRepository.deleteByCodValuta(currencyCode);
    }
}
