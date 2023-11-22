package md.orange.exchangeapp.controller;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.entity.CursValutar;
import md.orange.exchangeapp.entity.CursValutarWithDictionarValute;
import md.orange.exchangeapp.exception.CurrencyNotFoundException;
import md.orange.exchangeapp.service.CurrencyService;
import md.orange.exchangeapp.service.RateService;
import md.orange.exchangeapp.dto.ExchangeRateDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rates")
@RequiredArgsConstructor
public class RateController {
    private final RateService rateService;
    private final CurrencyService currencyService;

    @PostMapping
    public Mono<ExchangeRateDto> saveExchangeRate(@RequestBody ExchangeRateDto exchangeRateDto) {
        var cursValutar = convertToEntity(exchangeRateDto);
        var cursValutarWithDictionarValute = currencyService.getCurrencyByCurrencyCode(exchangeRateDto.getCodValuta())
                .switchIfEmpty(Mono.error(new CurrencyNotFoundException()))
                .flatMap(dictionarValute -> {
                    cursValutar.setValutaId(dictionarValute.getId());

                    return Mono.just(cursValutar);
                });

        return rateService.saveCursValutar(cursValutarWithDictionarValute).map(this::convertToDto);
    }

    @GetMapping("/all")
    public Flux<ExchangeRateDto> getAllExchangeRate() {
        return rateService.getCursValutar()
                .map(this::convertToDto);
    }

    @GetMapping
    public Mono<ExchangeRateDto> getExchangeRateByCurrencyCode(@RequestParam("currencyCode") String currencyCode) {
        return rateService.getCursValutarByCurrencyCode(currencyCode)
                .switchIfEmpty(Mono.error(new CurrencyNotFoundException()))
                .map(this::convertToDto);
    }



    private ExchangeRateDto convertToDto(CursValutarWithDictionarValute cursValutarWithDictionarValute) {
        var exchangeRateDto = new ExchangeRateDto();

        var cursValutar = cursValutarWithDictionarValute.getCursValutar();
        var dictionarValute = cursValutarWithDictionarValute.getDictionarValute();

        exchangeRateDto.setCodValuta(dictionarValute.getCodValuta());
        exchangeRateDto.setRata(1d);
        exchangeRateDto.setCurs(cursValutar.getCurs());

        return exchangeRateDto;
    }

    private CursValutar convertToEntity(ExchangeRateDto exchangeRateDto) {
        var cursValutar = new CursValutar();

        cursValutar.setCurs(normalizeCurs(exchangeRateDto.getCurs(), exchangeRateDto.getRata()));
        return cursValutar;
    }

    private Double normalizeCurs(Double curs, Double rata) {
        return curs / rata;
    }
}
