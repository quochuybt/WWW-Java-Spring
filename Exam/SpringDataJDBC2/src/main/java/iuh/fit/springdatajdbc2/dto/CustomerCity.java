package iuh.fit.springdatajdbc2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerCity {
    private String city;
    private Long sum;
}
