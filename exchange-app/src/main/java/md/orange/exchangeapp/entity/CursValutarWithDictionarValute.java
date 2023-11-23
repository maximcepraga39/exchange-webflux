package md.orange.exchangeapp.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursValutarWithDictionarValute {
    private CursValutar cursValutar;
    private DictionarValute dictionarValute;
}
