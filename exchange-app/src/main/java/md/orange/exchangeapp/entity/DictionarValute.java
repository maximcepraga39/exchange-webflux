package md.orange.exchangeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dictionar_valute")
public class DictionarValute {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "cod_valuta")
    private String codValuta;
    @Column(name = "nume_valuta")
    private String numeValuta;
}
