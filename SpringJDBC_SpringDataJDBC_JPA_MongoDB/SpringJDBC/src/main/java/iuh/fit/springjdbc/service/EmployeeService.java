package iuh.fit.springjdbc.service;

import iuh.fit.springjdbc.dto.EmployeeForm;
import iuh.fit.springjdbc.dto.EmployeeSearchCriteria;
import iuh.fit.springjdbc.model.Employee;
import iuh.fit.springjdbc.repo.EmployeeRepository;
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

    public EmployeeForm getForm(Long id) {
        Employee emp = findById(id);
        if (emp == null) return null;
        EmployeeForm form = new EmployeeForm();
        form.setId(emp.getId());
        form.setName(emp.getName());
        form.setAge(emp.getAge());
        form.setSalary(emp.getSalary());
        // Lấy Id trực tiếp từ Employee
        form.setDepartmentId(emp.getDepartmentId());
        form.setPositionId(emp.getPositionId());
        return form;
    }

    public Employee save(Long id, EmployeeForm form) {
        Employee emp = (id != null) ? findById(id) : new Employee();
        if (emp == null) emp = new Employee();

        emp.setId(id);
        emp.setName(form.getName());
        emp.setAge(form.getAge());
        emp.setSalary(form.getSalary());
        // Gán Id trực tiếp thay vì gán Object Department/Position
        emp.setDepartmentId(form.getDepartmentId());
        emp.setPositionId(form.getPositionId());

        employeeRepository.save(emp);
        return emp;
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> search(EmployeeSearchCriteria criteria) {
        if (criteria.getId() != null) {
            Employee emp = findById(criteria.getId());
            return emp != null ? List.of(emp) : List.of();
        }
        if (criteria.getName() != null && !criteria.getName().isBlank()) {
            return employeeRepository.findByNameContainingIgnoreCase(criteria.getName());
        }
        if (criteria.getMinAge() != null && criteria.getMaxAge() != null) {
            return employeeRepository.findByAgeBetween(criteria.getMinAge(), criteria.getMaxAge());
        }
        if (criteria.getMinSalary() != null) {
            return employeeRepository.findBySalaryGreaterThan(criteria.getMinSalary());
        }
        if (criteria.getPositionId() != null) {
            return employeeRepository.findByPositionId(criteria.getPositionId());
        }
        return findAll();
    }
}