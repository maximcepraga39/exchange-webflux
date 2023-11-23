package md.orange.exchangeapp.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NumerarWithDictionarValuteAndCasaDeSchimb {
    private Numerar numerar;
    private DictionarValute dictionarValute;
    private CasaDeSchimb casaDeSchimb;
}
