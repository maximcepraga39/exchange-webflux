package md.orange.exchangeapp.repository;

import md.orange.exchangeapp.entity.SchimbValutar;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchimbValutarRepository extends R2dbcRepository<SchimbValutar, Integer> {
}