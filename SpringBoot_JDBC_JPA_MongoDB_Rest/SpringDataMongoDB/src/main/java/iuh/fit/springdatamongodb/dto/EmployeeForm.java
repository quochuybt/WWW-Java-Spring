package iuh.fit.springdatamongodb.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeForm {
    private String id;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @Min(value = 18, message = "Tuổi phải từ 18 trở lên")
    private int age;

    @Positive(message = "Lương phải lớn hơn 0")
    private double salary;

    private String departmentId;
    private String positionId;
}