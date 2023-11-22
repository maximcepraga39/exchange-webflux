package md.orange.exchangeapp.controller;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.dto.CurrencyDto;
import md.orange.exchangeapp.entity.DictionarValute;
import md.orange.exchangeapp.service.CurrencyService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @PostMapping
    public Mono<CurrencyDto> addCurrency(@RequestBody CurrencyDto currencyDto) {
        return currencyService.addCurrency(convertToEntity(currencyDto))
                .map(this::convertToDto);
    }

    @GetMapping("/all")
    public Flux<CurrencyDto> getAllCurrencies() {
        return currencyService.getAllCurrencies()
                .map(this::convertToDto);
    }

    @GetMapping
    public Mono<CurrencyDto> getCurrencyByCurrencyCode(@RequestParam("currencyCode") String currencyCode) {
        return currencyService.getCurrencyByCurrencyCode(currencyCode)
                .map(this::convertToDto);
    }

    @DeleteMapping
    public Mono<CurrencyDto> deleteCurrency(@RequestParam("currencyCode") String currencyCode) {
        return currencyService.deleteCurrency(currencyCode)
                .map(this::convertToDto);
    }

    private CurrencyDto convertToDto(DictionarValute dictionarValute) {
        var currencyDto = new CurrencyDto();
        currencyDto.setCurrencyCode(dictionarValute.getCodValuta());
        currencyDto.setCurrencyName(dictionarValute.getNumeValuta());
        return currencyDto;
    }

    private DictionarValute convertToEntity(CurrencyDto currencyDto) {
        var dictionarValute = new DictionarValute();
        dictionarValute.setCodValuta(currencyDto.getCurrencyCode());
        dictionarValute.setNumeValuta(currencyDto.getCurrencyName());
        return dictionarValute;
    }
}
