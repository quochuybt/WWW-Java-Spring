package iuh.fit.springdatajpa1.service;

import iuh.fit.springdatajpa1.model.Employee;
import iuh.fit.springdatajpa1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee empDetails) {
        Employee existing = findById(id);
        if (existing==null) return null;
        existing.setName(empDetails.getName());
        existing.setAge(empDetails.getAge());
        existing.setSalary(empDetails.getSalary());
        existing.setDepartment(empDetails.getDepartment());
        existing.setPosition(empDetails.getPosition());
        return employeeRepository.save(existing);
    }

    public boolean delete(Long id) {
        if (!employeeRepository.existsById(id)) return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> filterBySalary(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
}
