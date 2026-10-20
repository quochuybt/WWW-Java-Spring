package iuh.fit.springdatajpa.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "positions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}