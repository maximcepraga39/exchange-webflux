package md.orange.exchangeapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExchangeRateDto {
    private String codValuta;
    private Double rata;
    private Double curs;
    private LocalDate dataCurs = LocalDate.now();
}
