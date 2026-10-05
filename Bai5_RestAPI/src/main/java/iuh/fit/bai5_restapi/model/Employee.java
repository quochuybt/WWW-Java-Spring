package iuh.fit.bai5_restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private int id;
    private String name;
    private String role;
    private double salary;
    private int departmentId;
}
