package md.orange.exchangeapp.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DictionarValute {
    @Id
    private Long id;
    private String codValuta;
    private String numeValuta;
}
