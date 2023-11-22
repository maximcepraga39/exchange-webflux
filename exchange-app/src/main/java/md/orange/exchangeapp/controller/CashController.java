package md.orange.exchangeapp.controller;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.dto.CashRqDto;
import md.orange.exchangeapp.dto.CashRsDto;
import md.orange.exchangeapp.dto.DeleteCacheRqDto;
import md.orange.exchangeapp.entity.DictionarValute;
import md.orange.exchangeapp.entity.Numerar;
import md.orange.exchangeapp.entity.NumerarWithDictionarValute;
import md.orange.exchangeapp.exception.CurrencyNotFoundException;
import md.orange.exchangeapp.service.CashService;
import md.orange.exchangeapp.service.CurrencyService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cash")
@RequiredArgsConstructor
public class CashController {
    private final CashService cashService;
    private final CurrencyService currencyService;

    @GetMapping("/all")
    public Flux<CashRsDto> getAllCash() {
        return cashService.getAllCash().map(this::convertToDto);
    }

    @GetMapping
    public Mono<CashRsDto> getCashByCurrencyCode(@RequestParam("currencyCode") String currencyCode) {
        return cashService.getCashByCurrencyCode(currencyCode).map(this::convertToDto);
    }

    @PostMapping
    public Mono<CashRsDto> addCash(@RequestBody CashRqDto cashRequest) {
        var numerar = convertToEntity(cashRequest);
        var cursValutarWithDictionarValute = currencyService.getCurrencyByCurrencyCode(cashRequest.getCodValuta())
                .switchIfEmpty(Mono.error(new CurrencyNotFoundException()))
                .flatMap(dictionarValute -> {
                    numerar.setValutaId(dictionarValute.getId());

                    return Mono.just(numerar);
                });
        return cursValutarWithDictionarValute.flatMap(cashService::addCash).map(this::convertToDto);
    }

    @PutMapping
    public Mono<CashRsDto> updateCash(@RequestBody CashRqDto cashRequest) {
        return cashService.updateCash(cashRequest).map(this::convertToDto);
    }

    @DeleteMapping
    public Mono<CashRsDto> deleteCash(@RequestParam String codValuta) {
        return cashService.deleteCash(codValuta).map(this::convertToDto);
    }

    private CashRsDto convertToDto(NumerarWithDictionarValute numerarWithDictionarValute) {
        var cashRsDto = new CashRsDto();
        var numerar = numerarWithDictionarValute.getNumerar();
        var dictionarValute = numerarWithDictionarValute.getDictionarValute();
        cashRsDto.setCodValuta(dictionarValute.getCodValuta());
        cashRsDto.setSuma(numerar.getSuma());
        return cashRsDto;
    }
    private Numerar convertToEntity(CashRqDto cashRequest) {
        var numerar = new Numerar();
        numerar.setSuma(cashRequest.getSuma());
        numerar.setUtilizator(cashRequest.getUtilizator());
        return numerar;
    }
}
