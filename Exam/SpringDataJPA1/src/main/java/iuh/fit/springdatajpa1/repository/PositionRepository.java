package iuh.fit.springdatajpa1.repository;

import iuh.fit.springdatajpa1.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
