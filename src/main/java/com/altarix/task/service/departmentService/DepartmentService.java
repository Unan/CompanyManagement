package com.altarix.task.service.departmentService;

import com.altarix.task.model.Department;
import com.altarix.task.model.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    Department generateFromDTO(DepartmentDTO departmentDTO);

    Department find(String name);

    Department find(Integer id);

    void create(Department department);

    void changeName(Department department, String requiredName);

    void delete(Department department);

    List<Department> sub(Department department);

    List<Department> under(Department department);

    void changeParent(Department department, Department requiredParentDepartment);

    List<Department> above(Department department);

    Integer employeesWageAmount(Department department);
}
