package iuh.fit.springdatajpa1.repository;

import iuh.fit.springdatajpa1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByAgeBetween(int minAge, int maxAge);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByPositionId(Long positionId);
}
