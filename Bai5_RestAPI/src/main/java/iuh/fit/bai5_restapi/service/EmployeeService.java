package iuh.fit.bai5_restapi.service;

import iuh.fit.bai5_restapi.model.Employee;
import iuh.fit.bai5_restapi.repo.EmployeeRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EmployeeService {

    @Inject
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.getAllEmployees();
    }

    public Employee getEmployeeById(int id){
        return employeeRepo.getEmployeeById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.addEmployee(employee);
    }

    public Employee updateEmployee(int id, Employee employee) {
        return employeeRepo.updateEmployee(id,employee);
    }


}
