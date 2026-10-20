package iuh.fit.springdatajdbc.dto;

import lombok.Data;

@Data
public class EmployeeSearchCriteria {
    private Long id;
    private String name;
    private Integer minAge;
    private Integer maxAge;
    private Double minSalary;
    private Long positionId;

    public boolean hasAny() {
        return id != null || (name != null && !name.isBlank())
                || minAge != null || maxAge != null
                || minSalary != null || positionId != null;
    }
}