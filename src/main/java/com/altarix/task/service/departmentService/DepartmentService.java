package com.altarix.task.service.departmentService;

import com.altarix.task.model.Department;

import java.util.Collection;

public interface DepartmentService {
    void createDepartment(Department department);

    void changeDepartmentName(String currentDepartmentName, String requiredDepartmentName);

    void deleteDepartment(String departmentName);

    Department departmentInfo(String departmentName);

    Collection<Department> nextSubDepartments(String departmentName);

    Collection<Department> allSubDepartments(String departmentName);

    void changeParentDepartment(String subDepartmentName, String parentDepartmentName);

    Collection<Department> allParentDepartments(String departmentName);

    Department searchDepartment(String departmentName);

    Integer departmentWageAmount(String departmentName);

    void changeDepartmentForEmployeesOfDepartment(String currentDepartmentName, String requiredDepartmentName);
}
