package iuh.fit.springdatajdbc.service;

import iuh.fit.springdatajdbc.dto.EmployeeForm;
import iuh.fit.springdatajdbc.dto.EmployeeSearchCriteria;
import iuh.fit.springdatajdbc.model.Employee;
import iuh.fit.springdatajdbc.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        employeeRepository.findAll().forEach(list::add);
        return list;
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
        // Data JDBC luu ID truc tiep, khong qua Entity
        form.setDepartmentId(emp.getDepartmentId());
        form.setPositionId(emp.getPositionId());
        return form;
    }

    public Employee save(Long id, EmployeeForm form) {
        Employee emp = (id != null) ? findById(id) : new Employee();
        if (emp == null) emp = new Employee();

        emp.setName(form.getName());
        emp.setAge(form.getAge());
        emp.setSalary(form.getSalary());
        // Gan Id truc tiep cho Employee
        emp.setDepartmentId(form.getDepartmentId());
        emp.setPositionId(form.getPositionId());

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