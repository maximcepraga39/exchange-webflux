package md.orange.exchangeapp.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Numerar {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long valutaId;
    private String utilizator;
    private Double suma;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data = LocalDate.now();
}
