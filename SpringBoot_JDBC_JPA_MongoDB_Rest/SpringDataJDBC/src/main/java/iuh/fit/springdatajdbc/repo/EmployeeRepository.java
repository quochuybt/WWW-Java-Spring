package iuh.fit.springdatajdbc.repo;

import iuh.fit.springdatajdbc.model.Employee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByNameContainingIgnoreCase(String name);
    List<Employee> findByAgeBetween(int minAge, int maxAge);
    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByPositionId(Long positionId);

    List<Employee> findByDepartmentIdAndPositionId(Long departmentId, Long positionId);

    @Query("SELECT d.name, COUNT(e.id) as total FROM department d LEFT JOIN employees e ON d.id = e.department_id GROUP BY d.id, d.name")
    List<Object[]> countEmployeesByDepartment();

    @Query("SELECT department_id, AVG(salary) as avg_sal, SUM(salary) as sum_sal FROM employees GROUP BY department_id")
    List<Object[]> getSalaryStatsByDepartment();

    @Query("SELECT * FROM employees WHERE department_id = :deptId ORDER BY salary DESC LIMIT :limitN")
    List<Employee> findTopNByDepartmentId(@Param("deptId") Long deptId, @Param("limitN") int limitN);
}