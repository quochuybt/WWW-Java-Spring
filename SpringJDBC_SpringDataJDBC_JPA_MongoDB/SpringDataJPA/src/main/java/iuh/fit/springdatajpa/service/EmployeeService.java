package iuh.fit.springdatajpa.service;

import iuh.fit.springdatajpa.dto.EmployeeForm;
import iuh.fit.springdatajpa.dto.EmployeeSearchCriteria;
import iuh.fit.springdatajpa.model.Department;
import iuh.fit.springdatajpa.model.Employee;
import iuh.fit.springdatajpa.model.Position;
import iuh.fit.springdatajpa.repo.DepartmentRepository;
import iuh.fit.springdatajpa.repo.EmployeeRepository;
import iuh.fit.springdatajpa.repo.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

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
        if (emp.getDepartment() != null) form.setDepartmentId(emp.getDepartment().getId());
        if (emp.getPosition() != null) form.setPositionId(emp.getPosition().getId());
        return form;
    }

    public Employee save(Long id, EmployeeForm form) {
        Employee emp = (id != null) ? findById(id) : new Employee();
        if (emp == null) emp = new Employee();

        emp.setName(form.getName());
        emp.setAge(form.getAge());
        emp.setSalary(form.getSalary());

        if (form.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(form.getDepartmentId()).orElse(null);
            emp.setDepartment(dept);
        }
        if (form.getPositionId() != null) {
            Position pos = positionRepository.findById(form.getPositionId()).orElse(null);
            emp.setPosition(pos);
        }

        return employeeRepository.save(emp);
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