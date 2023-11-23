package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.Numerar;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface NumerarRepository extends R2dbcRepository<Numerar, Long> {
    @Query("SELECT * FROM numerar n " +
            "INNER JOIN dictionar_valute dv ON n.valuta_id = dv.id " +
            "INNER JOIN casa_de_schimb cds ON n.casa_de_schimb_id = cds.id " +
            "WHERE dv.cod_valuta = :codValuta AND cds.nume = :numeCasaDeSchimb")
    Mono<Numerar> findByCodValutaAndNumeCasaDeSchimb(String codValuta, String numeCasaDeSchimb);

    @Modifying
    @Query("UPDATE numerar SET suma = :suma WHERE id = :numerarId")
    Mono<Numerar> updateNumerarById(Long numerarId, Double suma);

    @Modifying
    @Query("UPDATE numerar SET suma = :suma " +
            "WHERE valuta_id = (SELECT id FROM dictionar_valute dv WHERE dv.cod_valuta = :codValuta)" +
            "AND casa_de_schimb_id = (SELECT id FROM casa_de_schimb cds WHERE cds.nume = :numeCasaDeSchimb)")
    Mono<Integer> updateNumerarByCodValutaAndNumeCasaDeSchimb(String codValuta, String numeCasaDeSchimb, Double suma);

    @Modifying
    @Query("DELETE FROM numerar " +
            "WHERE valuta_id = (SELECT id FROM dictionar_valute dv WHERE dv.cod_valuta = :codValuta)" +
            "AND casa_de_schimb_id = (SELECT id FROM casa_de_schimb cds WHERE cds.nume = :numeCasaDeSchimb)")
    Mono<Integer> deleteNumerarByCodValuta(String codValuta, String numeCasaDeSchimb);
}