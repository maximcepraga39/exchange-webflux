package md.orange.exchangeapp.controller;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.dto.CashRqDto;
import md.orange.exchangeapp.dto.CashRsDto;
import md.orange.exchangeapp.entity.Numerar;
import md.orange.exchangeapp.entity.NumerarWithDictionarValuteAndCasaDeSchimb;
import md.orange.exchangeapp.exception.CasaDeSchimbNotFoundException;
import md.orange.exchangeapp.exception.CurrencyNotFoundException;
import md.orange.exchangeapp.repository.CasaDeSchimbRepository;
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
    private final CasaDeSchimbRepository casaDeSchimbRepository;

    @GetMapping("/all")
    public Flux<CashRsDto> getAllCash() {
        return cashService.getAllCash().map(this::convertToDto);
    }

    @GetMapping
    public Mono<CashRsDto> getCashByCurrencyCode(@RequestParam("currencyCode") String currencyCode, @RequestParam("numeCasaDeSchimb") String numeCasaDeSchimb) {
        return cashService.getCashByCurrencyCodeAndCasaDeSchimb(currencyCode, numeCasaDeSchimb)
                .map(this::convertToDto);
    }

    @PostMapping
    public Mono<CashRsDto> addCash(@RequestBody CashRqDto cashRequest) {
        var numerar = convertToEntity(cashRequest);
        var cursValutarWithDictionarValuteAndCasaDeSchimb = currencyService.getCurrencyByCurrencyCode(cashRequest.getCodValuta())
                .switchIfEmpty(Mono.error(new CurrencyNotFoundException()))
                .flatMap(dictionarValute -> {
                    numerar.setValutaId(dictionarValute.getId());

                    return Mono.just(numerar);
                })
                .flatMap(savedNumerar -> casaDeSchimbRepository.findByNume(cashRequest.getNumeCasaDeSchimb())
                        .switchIfEmpty(Mono.error(new CasaDeSchimbNotFoundException(cashRequest.getNumeCasaDeSchimb())))
                        .map(casaDeSchimb -> {
                            savedNumerar.setCasaDeSchimbId(casaDeSchimb.getId());
                            return savedNumerar;
                        }));

        return cursValutarWithDictionarValuteAndCasaDeSchimb.flatMap(cashService::addCash).map(this::convertToDto);
    }

    @PutMapping
    public Mono<Integer> updateCash(@RequestBody CashRqDto cashRequest) {
        return cashService.updateCash(cashRequest);
    }

    @DeleteMapping
    public Mono<Integer> deleteCash(@RequestParam("codValuta") String codValuta, @RequestParam("numeCasaDeSchimb") String numeCasaDeSchimb) {
        return cashService.deleteCash(codValuta, numeCasaDeSchimb);
    }

    private CashRsDto convertToDto(NumerarWithDictionarValuteAndCasaDeSchimb numerarWithDictionarValuteAndCasaDeSchimb) {
        var cashRsDto = new CashRsDto();
        var numerar = numerarWithDictionarValuteAndCasaDeSchimb.getNumerar();
        var dictionarValute = numerarWithDictionarValuteAndCasaDeSchimb.getDictionarValute();
        var casaDeSchimb = numerarWithDictionarValuteAndCasaDeSchimb.getCasaDeSchimb();
        cashRsDto.setCodValuta(dictionarValute.getCodValuta());
        cashRsDto.setSuma(numerar.getSuma());
        cashRsDto.setNumeCasaDeSchimb(casaDeSchimb.getNume());
        cashRsDto.setAdresaCasaDeSchimb(casaDeSchimb.getAdresa());
        return cashRsDto;
    }
    private Numerar convertToEntity(CashRqDto cashRequest) {
        var numerar = new Numerar();
        numerar.setSuma(cashRequest.getSuma());
        numerar.setUtilizator(cashRequest.getUtilizator());
        return numerar;
    }
}
