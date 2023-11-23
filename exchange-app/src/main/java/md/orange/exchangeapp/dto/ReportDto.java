package md.orange.exchangeapp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReportDto {
    private String adresaCasaDeSchimb;
    private String codValuta;
    private Integer totalTranzactii;
    private Double sumaPrimita;
    private Double sumaEliberata;
    private LocalDate dataRaport;
}
