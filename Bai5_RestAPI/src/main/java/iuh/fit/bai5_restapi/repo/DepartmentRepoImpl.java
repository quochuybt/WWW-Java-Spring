package iuh.fit.bai5_restapi.repo;


import iuh.fit.bai5_restapi.model.Department;
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
public class DepartmentRepoImpl implements DepartmenRepo {

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
    public List<Department> findAllDepartment() {
        List<Department> list = new ArrayList<>();
        String sql = """
                SELECT * from departments
                """;
        try (Connection con = getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Department department = new Department(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                list.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Department findDepartmentById(int id) {
        Department dep = null;
        String sql = "SELECT * FROM departments WHERE id = ?";
        try (Connection con = getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Department(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dep;
    }

    @Override
    public Department addDepartment(Department department) {
        String sql = "INSERT INTO departments(name) VALUES (?)";

        try (Connection con = getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql,
                     java.sql.Statement.RETURN_GENERATED_KEYS
             )) {

            ps.setString(1, department.getName());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return new Department(
                                rs.getInt(1),
                                department.getName()
                        );
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Department updateDepartment(int id, Department department) {
        String sql = "UPDATE departments SET name = ? WHERE id = ?";

        try (Connection con = getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, department.getName());
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return new Department(
                        id,
                        department.getName()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
