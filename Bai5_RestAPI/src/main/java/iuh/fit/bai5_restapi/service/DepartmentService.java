package iuh.fit.bai5_restapi.service;

import iuh.fit.bai5_restapi.model.Department;
import iuh.fit.bai5_restapi.model.Employee;
import iuh.fit.bai5_restapi.repo.DepartmenRepo;
import jakarta.inject.Inject;

import java.util.List;

public class DepartmentService {

    @Inject
    private DepartmenRepo departmenRepo;

    public List<Department> getAllDepartments() {
        return departmenRepo.findAllDepartment();
    }

    public Department getDepartmentById(int id){
        return departmenRepo.findDepartmentById(id);
    }

    public Department addDepartment(Department department) {
        return departmenRepo.addDepartment(department);
    }

    public Department updateDepartment(int id, Department department) {
        return departmenRepo.updateDepartment(id,department);}
}
