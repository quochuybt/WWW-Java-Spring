package iuh.fit.springdatamongodb.dto;

import lombok.Data;

@Data
public class EmployeeSearchCriteria {
    private String id;
    private String name;
    private Integer minAge;
    private Integer maxAge;
    private Double minSalary;
    private String positionId;

    public boolean hasAny() {
        return id != null || (name != null && !name.isBlank())
                || minAge != null || maxAge != null
                || minSalary != null || positionId != null;
    }
}