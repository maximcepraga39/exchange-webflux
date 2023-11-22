package md.orange.exchangeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchimbValutar {
    @Id
    private Integer id;
    private Integer valutaId;
    private Double cursSchimb;
    private Double sumaPrimita;
    private Double sumaEliberata;
    private String utilizator;
    private LocalDateTime data;
}
