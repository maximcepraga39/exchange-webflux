package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.CursValutar;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface CursValutarRepository extends R2dbcRepository<CursValutar, Long> {
    @Query("SELECT * FROM curs_valutar cv WHERE data_curs = :dataCurs")
    Flux<CursValutar> findAllByDataCurs(LocalDate dataCurs);

    @Query("SELECT * FROM curs_valutar cv INNER JOIN dictionar_valute dv ON cv.valuta_id = dv.id WHERE dv.cod_valuta = :codValuta and cv.data_curs = :dataCurs")
    Mono<CursValutar> findByCodValutaAndDataCurs(String codValuta, LocalDate dataCurs);
}