package iuh.fit.springdatajdbc2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customers")
public class Customer {
    @Id
    private Long id;
    private String fullName;
    private String email;
    private String city;
}
