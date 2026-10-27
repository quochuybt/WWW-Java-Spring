package iuh.fit.springdatajpa2.controller;

import iuh.fit.springdatajpa2.entity.Employee;
import iuh.fit.springdatajpa2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Bắt buộc phải là RestController để trả về JSON
@RequestMapping("/api/employees") // Tiền tố chung cho mọi API trong class này
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // ==========================================
    // 1. API PHÂN TRANG & SẮP XẾP
    // URL: GET http://localhost:8080/api/employees/page?page=0&size=5
    // ==========================================
    @GetMapping("/page")
    public Page<Employee> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return employeeService.getEmployeesPaginated(page, size);
    }

    // ==========================================
    // 2. API TÌM THEO TÊN PHÒNG BAN
    // URL: GET http://localhost:8080/api/employees/department?name=IT
    // ==========================================
    @GetMapping("/department")
    public List<Employee> getByDepartmentName(@RequestParam String name) {
        return employeeService.findEmployeeByDepartmentName(name);
    }

    // ==========================================
    // 3. API TÌM THEO LƯƠNG LỚN HƠN
    // URL: GET http://localhost:8080/api/employees/salary-greater?min=1000
    // ==========================================
    @GetMapping("/salary-greater")
    public List<Employee> getBySalaryGreaterThan(@RequestParam double min) {
        return employeeService.findEmployeeBySalaryGreaterThan(min);
    }

    // ==========================================
    // 4. API TÌM THEO TÊN (CHỨA TỪ KHÓA)
    // URL: GET http://localhost:8080/api/employees/search?name=An
    // ==========================================
    @GetMapping("/search")
    public List<Employee> getByFullNameContaining(@RequestParam String name) {
        return employeeService.findEmployeeByFullNameContainingIgnoreCase(name);
    }

    // ==========================================
    // 5. API LẤY TOP 3 LƯƠNG CAO NHẤT (Native SQL)
    // URL: GET http://localhost:8080/api/employees/top3-salary
    // ==========================================
    @GetMapping("/top3-salary")
    public List<Employee> getTop3Salary() {
        return employeeService.getTop3HighestSalary();
    }

    // ==========================================
    // 6. API TÍNH LƯƠNG TRUNG BÌNH THEO PHÒNG BAN (JPQL)
    // URL: GET http://localhost:8080/api/employees/avg-salary?departmentName=IT
    // ==========================================
    @GetMapping("/avg-salary")
    public Double getAverageSalary(@RequestParam String departmentName) {
        return employeeService.getAverageSalary(departmentName);
    }
}