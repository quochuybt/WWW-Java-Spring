package iuh.fit.springjdbc.repo;

import iuh.fit.springjdbc.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Department> rowMapper = (rs, rowNum) -> new Department(
            rs.getLong("id"),
            rs.getString("name")
    );

    public List<Department> findAll() {
        String sql = "SELECT * FROM department";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Department findById(Long id) {
        String sql = "SELECT * FROM department WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // 3.6. Danh sách Department chưa có Employee nào (dùng SQL thuần + RowMapper)[cite: 5]
    public List<Department> findDepartmentsWithoutEmployees() {
        String sql = "SELECT d.* FROM department d LEFT JOIN employees e ON d.id = e.department_id WHERE e.id IS NULL";
        return jdbcTemplate.query(sql, rowMapper);
    }
}