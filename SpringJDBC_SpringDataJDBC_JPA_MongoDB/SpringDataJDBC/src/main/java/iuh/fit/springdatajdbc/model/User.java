package iuh.fit.springdatajdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String role;
}