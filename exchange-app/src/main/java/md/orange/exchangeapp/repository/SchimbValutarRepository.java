package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.SchimbValutar;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface SchimbValutarRepository extends R2dbcRepository<SchimbValutar, Integer> {
    @Query("SELECT * FROM schimb_valutar sv WHERE sv.data_schimb = :dataSchimb")
    Flux<SchimbValutar> findAllByDataSchimb(LocalDate dataSchimb);
}