package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.DictionarValute;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DictionarValuteRepository extends R2dbcRepository<DictionarValute, Long> {
    @Query("SELECT * FROM dictionar_valute dv WHERE dv.id = :id")
    Mono<DictionarValute> findById(Long id);

    @Query("SELECT * FROM dictionar_valute dv WHERE dv.cod_valuta = :codValuta")
    Mono<DictionarValute> findByCodValuta(String codValuta);

    @Modifying
    @Query("DELETE FROM dictionar_valute WHERE cod_valuta = :cod_valuta")
    Mono<DictionarValute> deleteByCodValuta(String cod_valuta);
}