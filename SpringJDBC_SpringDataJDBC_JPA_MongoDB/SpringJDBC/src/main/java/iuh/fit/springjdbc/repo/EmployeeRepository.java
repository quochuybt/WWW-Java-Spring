package iuh.fit.springjdbc.repo;

import iuh.fit.springjdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper biến từng dòng ResultSet thành Object Employee[cite: 4, 5]
    private final RowMapper<Employee> rowMapper = (rs, rowNum) -> new Employee(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("age"),
            rs.getDouble("salary"),
            rs.getObject("department_id") != null ? rs.getLong("department_id") : null,
            rs.getObject("position_id") != null ? rs.getLong("position_id") : null
    );

    // 1. Thêm / Sửa[cite: 4]
    public int save(Employee emp) {
        if (emp.getId() == null) {
            String sql = "INSERT INTO employees (name, age, salary, department_id, position_id) VALUES (?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, emp.getName(), emp.getAge(), emp.getSalary(), emp.getDepartmentId(), emp.getPositionId());
        } else {
            String sql = "UPDATE employees SET name = ?, age = ?, salary = ?, department_id = ?, position_id = ? WHERE id = ?";
            return jdbcTemplate.update(sql, emp.getName(), emp.getAge(), emp.getSalary(), emp.getDepartmentId(), emp.getPositionId(), emp.getId());
        }
    }

    // 1. Xóa[cite: 4]
    public int deleteById(Long id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // 1. Xem danh sách[cite: 4]
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // 2. Tìm theo ID[cite: 4]
    public Optional<Employee> findById(Long id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        List<Employee> list = jdbcTemplate.query(sql, rowMapper, id);
        return list.stream().findFirst();
    }

    // 2. Tìm theo Name[cite: 4]
    public List<Employee> findByNameContainingIgnoreCase(String name) {
        String sql = "SELECT * FROM employees WHERE LOWER(name) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, rowMapper, "%" + name + "%");
    }

    // 2. Tìm theo Age (WHERE age BETWEEN ? AND ?)[cite: 4]
    public List<Employee> findByAgeBetween(int minAge, int maxAge) {
        String sql = "SELECT * FROM employees WHERE age BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rowMapper, minAge, maxAge);
    }

    // 2. Tìm theo Salary (WHERE salary > ?)[cite: 4]
    public List<Employee> findBySalaryGreaterThan(double salary) {
        String sql = "SELECT * FROM employees WHERE salary > ?";
        return jdbcTemplate.query(sql, rowMapper, salary);
    }

    // 3.1. Danh sách Employee theo Position ID[cite: 5]
    public List<Employee> findByPositionId(Long positionId) {
        String sql = "SELECT * FROM employees WHERE position_id = ?";
        return jdbcTemplate.query(sql, rowMapper, positionId);
    }

    // 3.2. Danh sách Employee theo Department ID và Position ID[cite: 5]
    public List<Employee> findByDepartmentIdAndPositionId(Long departmentId, Long positionId) {
        String sql = "SELECT * FROM employees WHERE department_id = ? AND position_id = ?";
        return jdbcTemplate.query(sql, rowMapper, departmentId, positionId);
    }

    // 3.3. Thống kê số Employee của mỗi Department[cite: 5]
    public List<Object[]> countEmployeesByDepartment() {
        String sql = "SELECT d.name, COUNT(e.id) FROM department d LEFT JOIN employees e ON d.id = e.department_id GROUP BY d.id, d.name";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{rs.getString(1), rs.getLong(2)});
    }

    // 3.4. Lương trung bình và tổng lương theo Department[cite: 5]
    public List<Object[]> getSalaryStatsByDepartment() {
        String sql = "SELECT department_id, AVG(salary), SUM(salary) FROM employees GROUP BY department_id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{rs.getLong(1), rs.getDouble(2), rs.getDouble(3)});
    }

    // 3.5. Top N Employee lương cao nhất trong một Department[cite: 5]
    public List<Employee> findTopNByDepartmentId(Long departmentId, int limitN) {
        String sql = "SELECT * FROM employees WHERE department_id = ? ORDER BY salary DESC LIMIT ?";
        return jdbcTemplate.query(sql, rowMapper, departmentId, limitN);
    }
}