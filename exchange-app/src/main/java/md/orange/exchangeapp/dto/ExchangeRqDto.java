package md.orange.exchangeapp.dto;

import lombok.Data;

@Data
public class ExchangeRqDto {
    private String numeCasaDeSchimb;
    private String codValuta;
    private Double suma;
    private String utilizator;
}
