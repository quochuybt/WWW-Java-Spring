package iuh.fit.bai5_restapi.repo;

import iuh.fit.bai5_restapi.model.Department;

import java.util.List;

public interface DepartmenRepo {
    List<Department> findAllDepartment();
    Department findDepartmentById(int id);
    Department addDepartment(Department department);
    Department updateDepartment(int id, Department department);
}
