package md.orange.exchangeapp.entity;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchimbValutar {
    @Id
    private Long id;
    private Long valutaId;
    private Long cursValutarId;
    private Long casaDeSchimbId;
    private Double sumaPrimita;
    private Double sumaEliberata;
    private String utilizator;
    private LocalDate dataSchimb = LocalDate.now();
}
