package md.orange.exchangeapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Angajati {
    @Id
    private Long id;
    private Long casaDeSchimbId;
    private String nume;
    private String prenume;
    private String functie;
    private LocalDateTime dataAngajarii;
}
