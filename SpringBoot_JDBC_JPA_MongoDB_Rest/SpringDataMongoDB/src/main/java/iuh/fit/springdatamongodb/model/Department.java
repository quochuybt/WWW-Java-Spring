package iuh.fit.springdatamongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Document(collection = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {
    @Id
    private String id;
    private String name;
    private List<Employee> employees;
}