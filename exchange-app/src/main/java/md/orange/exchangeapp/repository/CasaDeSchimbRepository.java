package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.CasaDeSchimb;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CasaDeSchimbRepository extends R2dbcRepository<CasaDeSchimb, Long> {
    @Query("SELECT * FROM casa_de_schimb cds WHERE cds.id = :id")
    Mono<CasaDeSchimb> findById(Long id);

    @Query("SELECT * FROM casa_de_schimb cds WHERE cds.nume = :nume")
    Mono<CasaDeSchimb> findByNume(String nume);
}
