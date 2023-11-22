package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.Numerar;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumerarRepository extends R2dbcRepository<Numerar, Integer> {
}