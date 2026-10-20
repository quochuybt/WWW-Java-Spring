package iuh.fit.springdatajdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("department")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {
    @Id
    private Long id;
    private String name;
}