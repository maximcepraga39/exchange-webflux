package md.orange.exchangeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class ExchangeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeAppApplication.class, args);
    }

}
