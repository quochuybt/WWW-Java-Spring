package iuh.fit.springdatajpa2.service;

import iuh.fit.springdatajpa2.entity.Employee;
import iuh.fit.springdatajpa2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getEmployeesPaginated(int pageNumber, int pageSize) {
        // PageNumber bắt đầu từ 0. Sort.by("salary").descending() là sắp xếp giảm dần
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("salary").descending());
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> findEmployeeByDepartmentName(String name) {
        return employeeRepository.findEmployeeByDepartmentName(name);
    };

    public List<Employee> findEmployeeBySalaryGreaterThan(double min) {
        return employeeRepository.findEmployeeBySalaryGreaterThan(min);
    };

    public List<Employee> findEmployeeByFullNameContainingIgnoreCase(String name) {
        return employeeRepository.findEmployeeByNameContainingIgnoreCase(name);
    };

//    public List<Employee> findEmployeeByEmail(String email) {
//        return employeeRepository.findEmployeeByEmail(email);
//    };

    public List<Employee> getTop3HighestSalary() {
        return employeeRepository.findTop3HighestSalary();
    }

    public Double getAverageSalary(String departmentName) {
        return employeeRepository.getAverageSalaryByDepartment(departmentName);
    }
}
