package iuh.fit.springdatajpa1.controller;

import iuh.fit.springdatajpa1.model.Employee;
import iuh.fit.springdatajpa1.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employee);

    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee saved = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id,@RequestBody Employee employee) {
        Employee updated = employeeService.update(id,employee);
        if (updated==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = employeeService.delete(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.searchByName(name));
    }

    @GetMapping("/salary")
    public ResponseEntity<List<Employee>> filterSalary(@RequestParam double min) {
        return ResponseEntity.ok(employeeService.filterBySalary(min));
    }
}
