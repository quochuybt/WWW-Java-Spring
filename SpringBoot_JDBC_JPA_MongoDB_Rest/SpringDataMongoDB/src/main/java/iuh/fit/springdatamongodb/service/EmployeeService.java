package iuh.fit.springdatamongodb.service;

import iuh.fit.springdatamongodb.dto.EmployeeForm;
import iuh.fit.springdatamongodb.dto.EmployeeSearchCriteria;
import iuh.fit.springdatamongodb.model.Department;
import iuh.fit.springdatamongodb.model.Employee;
import iuh.fit.springdatamongodb.repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> findAll() {
        List<Employee> allEmployees = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();

        for (Department dept : departments) {
            if (dept.getEmployees() != null) {
                for (Employee emp : dept.getEmployees()) {
                    emp.setDepartmentId(dept.getId());
                    allEmployees.add(emp);
                }
            }
        }
        return allEmployees;
    }

    public Employee findById(String id) {
        return findAll().stream()
                .filter(e -> e.getId() != null && e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public EmployeeForm getForm(String id) {
        Employee emp = findById(id);
        if (emp == null) return null;
        EmployeeForm form = new EmployeeForm();
        form.setId(emp.getId());
        form.setName(emp.getName());
        form.setAge(emp.getAge());
        form.setSalary(emp.getSalary());
        form.setDepartmentId(emp.getDepartmentId());
        form.setPositionId(emp.getPositionId());
        return form;
    }

    public Employee save(String id, EmployeeForm form) {
        String deptId = form.getDepartmentId();
        if (deptId == null || deptId.isBlank()) return null;

        Department dept = departmentRepository.findById(deptId).orElse(null);
        if (dept == null) return null;

        if (dept.getEmployees() == null) {
            dept.setEmployees(new ArrayList<>());
        }

        Employee emp;
        if (id != null && !id.isBlank()) {
            emp = dept.getEmployees().stream()
                    .filter(e -> e.getId().equals(id))
                    .findFirst()
                    .orElse(new Employee());
            emp.setName(form.getName());
            emp.setAge(form.getAge());
            emp.setSalary(form.getSalary());
            emp.setPositionId(form.getPositionId());
        } else {
            emp = new Employee();
            emp.setId(UUID.randomUUID().toString().substring(0, 8));
            emp.setName(form.getName());
            emp.setAge(form.getAge());
            emp.setSalary(form.getSalary());
            emp.setPositionId(form.getPositionId());
            dept.getEmployees().add(emp);
        }

        departmentRepository.save(dept);
        return emp;
    }

    public void delete(String id) {
        List<Department> departments = departmentRepository.findAll();
        for (Department dept : departments) {
            if (dept.getEmployees() != null) {
                boolean removed = dept.getEmployees().removeIf(e -> e.getId().equals(id));
                if (removed) {
                    departmentRepository.save(dept);
                    break;
                }
            }
        }
    }

    public List<Employee> search(EmployeeSearchCriteria criteria) {
        List<Employee> list = findAll();

        if (criteria.getId() != null && !criteria.getId().isBlank()) {
            return list.stream().filter(e -> e.getId().equals(criteria.getId())).collect(Collectors.toList());
        }
        if (criteria.getName() != null && !criteria.getName().isBlank()) {
            return list.stream().filter(e -> e.getName().toLowerCase().contains(criteria.getName().toLowerCase())).collect(Collectors.toList());
        }
        if (criteria.getMinSalary() != null) {
            return list.stream().filter(e -> e.getSalary() >= criteria.getMinSalary()).collect(Collectors.toList());
        }
        return list;
    }
}