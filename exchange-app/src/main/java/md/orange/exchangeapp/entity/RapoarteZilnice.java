package md.orange.exchangeapp.entity;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RapoarteZilnice {
    @Id
    private Long id;
    private Long casaDeSchimbId;
    private Long valutaId;
    private Integer totalTranzactii;
    private Double sumaPrimita;
    private Double sumaEliberata;
    private LocalDate dataRaport = LocalDate.now();
}
