package md.orange.exchangeapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CurrencyNotFoundException.class)
    public Mono<ResponseEntity<String>> handleCurrencyNotFoundException(CurrencyNotFoundException e) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()));
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public Mono<ResponseEntity<String>> handleNotEnoughMoneyException(NotEnoughMoneyException e) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage()));
    }

    @ExceptionHandler(CasaDeSchimbNotFoundException.class)
    public Mono<ResponseEntity<String>> handleCasaDeSchimbNotFoundException(CasaDeSchimbNotFoundException e) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage()));
    }
}
