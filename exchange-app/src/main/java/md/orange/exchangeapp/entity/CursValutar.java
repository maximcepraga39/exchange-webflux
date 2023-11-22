package md.orange.exchangeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursValutar {
    @Id
    private Long id;
    @Column(name = "valuta_id")
    private Long valutaId;
    @Column(name = "curs")
    private Double curs;
    @Column(name = "data_curs")
    private LocalDate dataCurs = LocalDate.now();
}
