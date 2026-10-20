package iuh.fit.springdatajdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    private Long id;
    private String name;
    private int age;
    private double salary;

    @Column("department_id")
    private Long departmentId;

    @Column("position_id")
    private Long positionId;
}