package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.CursValutar;
import md.orange.exchangeapp.entity.CursValutarWithDictionarValute;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CursValutarRepository extends R2dbcRepository<CursValutar, Long> {
    @Query("SELECT * FROM curs_valutar cv INNER JOIN dictionar_valute dv ON cv.valuta_id = dv.id WHERE dv.cod_valuta = :codValuta")
    Mono<CursValutar> findByCodValuta(String codValuta);
}