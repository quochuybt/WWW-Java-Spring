package iuh.fit.bai5_restapi.repo;

import iuh.fit.bai5_restapi.model.Employee;

import java.util.List;

public interface EmployeeRepo {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(int id, Employee employee);
}
