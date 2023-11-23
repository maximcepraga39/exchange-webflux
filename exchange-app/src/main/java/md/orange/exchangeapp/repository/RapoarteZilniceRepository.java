package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.RapoarteZilnice;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface RapoarteZilniceRepository extends R2dbcRepository<RapoarteZilnice, Long> {
    @Query("SELECT * FROM rapoarte_zilnice rz WHERE rz.data_raport = :dataRaport")
    Flux<RapoarteZilnice> findByDate(LocalDate dataRaport);
}
