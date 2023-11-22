package md.orange.exchangeapp.dto;

import lombok.Data;

@Data
public class ExchangeRateDto {
    private String codValuta;
    private Double rata;
    private Double curs;
}
