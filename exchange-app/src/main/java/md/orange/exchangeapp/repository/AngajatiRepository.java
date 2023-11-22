package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.Angajati;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AngajatiRepository extends R2dbcRepository<Angajati, Integer> {
}