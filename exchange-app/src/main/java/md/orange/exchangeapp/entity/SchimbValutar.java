package md.orange.exchangeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchimbValutar {
    @Id
    private Long id;
    private Long valutaId;
    private Long cursValutarId;
    private Double sumaPrimita;
    private Double sumaEliberata;
    private String utilizator;
    private LocalDate data_schimb = LocalDate.now();
}
