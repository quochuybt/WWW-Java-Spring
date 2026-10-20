package iuh.fit.springdatajdbc.repo;

import iuh.fit.springdatajdbc.model.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {}