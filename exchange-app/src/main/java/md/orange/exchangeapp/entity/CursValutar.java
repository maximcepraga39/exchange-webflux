package md.orange.exchangeapp.entity;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursValutar {
    @Id
    private Long id;
    private Long valutaId;
    private Double curs;
    private LocalDate dataCurs = LocalDate.now();
}
