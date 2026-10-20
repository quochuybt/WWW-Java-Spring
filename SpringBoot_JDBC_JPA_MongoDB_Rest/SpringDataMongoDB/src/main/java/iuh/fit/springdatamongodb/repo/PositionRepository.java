package iuh.fit.springdatamongodb.repo;

import iuh.fit.springdatamongodb.model.Position;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends MongoRepository<Position, Long> {}