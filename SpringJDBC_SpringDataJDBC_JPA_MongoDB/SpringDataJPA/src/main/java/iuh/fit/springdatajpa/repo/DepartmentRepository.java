package iuh.fit.springdatajpa.repo;

import iuh.fit.springdatajpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d LEFT JOIN d.employees e WHERE e IS NULL")
    List<Department> findDepartmentsWithoutEmployees();
}
