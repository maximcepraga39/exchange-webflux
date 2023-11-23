package md.orange.exchangeapp.entity;

import lombok.*;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasaDeSchimb {
    @Id
    private Long id;
    private String nume;
    private String adresa;
    private String telefon;
    private String email;
    private LocalDate dataInfiintare;
}
