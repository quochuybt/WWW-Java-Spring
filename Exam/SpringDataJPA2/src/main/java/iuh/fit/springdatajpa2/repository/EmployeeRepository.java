package iuh.fit.springdatajpa2.repository;

import iuh.fit.springdatajpa2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findEmployeeByDepartmentName(String name);

    List<Employee> findEmployeeBySalaryGreaterThan(double min);

    List<Employee> findEmployeeByNameContainingIgnoreCase(String name);

//    @Query("select e from Employee e where e.email = :email")
//    List<Employee> findEmployeeByEmail(@Param("email") String email);

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.name = :deptName")
    Double getAverageSalaryByDepartment(@Param("deptName") String deptName);

    @Query(value = "SELECT * FROM employees ORDER BY salary DESC LIMIT 3", nativeQuery = true)
    List<Employee> findTop3HighestSalary();
}
