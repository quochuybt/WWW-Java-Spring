package iuh.fit.springdatajdbc.repo;

import iuh.fit.springdatajdbc.model.Department;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query("SELECT d.* FROM department d LEFT JOIN employees e ON d.id = e.department_id WHERE e.id IS NULL")
    List<Department> findDepartmentsWithoutEmployees();
}