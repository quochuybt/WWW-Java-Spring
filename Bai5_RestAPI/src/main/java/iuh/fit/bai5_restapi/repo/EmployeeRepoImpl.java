package iuh.fit.bai5_restapi.repo;

import iuh.fit.bai5_restapi.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeRepoImpl implements EmployeeRepo{

    private Employee maprow(ResultSet rs) throws SQLException{
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getDouble("salary"),
                rs.getInt("department_id")
        );
    }

    private volatile DataSource dataSource;

    private DataSource getDataSource() {
        if (dataSource == null) {
            try {
                Context env = (Context) new InitialContext().lookup("java:comp/env");
                dataSource = (DataSource) env.lookup("jdbc/hrdb");
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }
        return dataSource;
    }


    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = """
                SELECT * from employees
                """;
        try (Connection con = getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) list.add(maprow(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee emp = null;
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection con = getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) emp = maprow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return emp;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name,role, salary, department_id) VALUES (?, ?, ?, ?)";
        try (Connection con = getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1,employee.getName());
            ps.setString(2,employee.getRole());
            ps.setDouble(3,employee.getSalary());
            ps.setInt(4,employee.getDepartmentId());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.setId(rs.getInt(1));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        String sql = "UPDATE employees  SET name = ?, role = ?, salary = ?, department_id = ? WHERE id = ?";
        try (Connection con = getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getRole());
            ps.setDouble(3, employee.getSalary());
            ps.setInt(4, employee.getDepartmentId());
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employee;
    }
}
