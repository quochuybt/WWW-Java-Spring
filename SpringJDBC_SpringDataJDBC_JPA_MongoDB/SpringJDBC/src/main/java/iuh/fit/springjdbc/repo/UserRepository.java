package iuh.fit.springjdbc.repo;

import iuh.fit.springjdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("role")
    );

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        return jdbcTemplate.query(sql, userRowMapper, username).stream().findFirst();
    }
}