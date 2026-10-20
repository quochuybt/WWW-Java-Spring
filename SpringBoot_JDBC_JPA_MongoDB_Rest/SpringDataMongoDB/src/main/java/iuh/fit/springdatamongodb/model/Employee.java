package iuh.fit.springdatamongodb.model;

import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Field("employeeId")
    private String id;

    private String name;
    private String role;
    private int age;
    private double salary;

    @Field("positionId")
    private String positionId;

    private String departmentId;
}