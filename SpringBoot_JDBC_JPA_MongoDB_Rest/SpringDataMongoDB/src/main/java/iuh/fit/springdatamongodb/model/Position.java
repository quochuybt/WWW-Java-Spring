package iuh.fit.springdatamongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "position")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Position {
    @Id
    private String id;
    private String name;
}