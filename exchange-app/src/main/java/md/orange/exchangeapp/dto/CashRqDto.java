package md.orange.exchangeapp.dto;

import lombok.Data;

@Data
public class CashRqDto {
    private String codValuta;
    private Double suma;
    private String utilizator;
    private String numeCasaDeSchimb;
}
