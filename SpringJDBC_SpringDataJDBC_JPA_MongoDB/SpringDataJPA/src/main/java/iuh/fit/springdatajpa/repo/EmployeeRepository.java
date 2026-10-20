package iuh.fit.springdatajpa.repo;

import iuh.fit.springdatajpa.model.Department;
import iuh.fit.springdatajpa.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByAgeBetween(int minAge, int maxAge);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByPositionId(Long positionId);

    List<Employee> findByDepartmentIdAndPositionId(Long departmentId, Long positionId);

    @Query("SELECT e.department.name, COUNT(e) FROM Employee e GROUP BY e.department.id, e.department.name")
    List<Object[]> countEmployeesByDepartment();

    @Query("SELECT e.department.id, AVG(e.salary), SUM(e.salary) FROM Employee e GROUP BY e.department.id")
    List<Object[]> getSalaryStatsByDepartment();

    List<Employee> findTop5ByDepartmentIdOrderBySalaryDesc(Long departmentId);

    List<Employee> findByDepartmentIdOrderBySalaryDesc(Long departmentId, Pageable pageable);
}