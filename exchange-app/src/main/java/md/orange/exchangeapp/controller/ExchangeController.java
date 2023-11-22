package md.orange.exchangeapp.controller;

import lombok.RequiredArgsConstructor;
import md.orange.exchangeapp.dto.ExchangeRqDto;
import md.orange.exchangeapp.dto.ExchangeRsDto;
import md.orange.exchangeapp.service.ExchangeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @PostMapping
    public Mono<ExchangeRsDto> exchange(@RequestBody ExchangeRqDto exchangeRequest) {
        return exchangeService.exchange(exchangeRequest);
    }
}
