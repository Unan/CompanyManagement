package com.altarix.task.service.employeeService;

import com.altarix.task.model.Department;
import com.altarix.task.model.Employee;

import java.util.Date;

public interface EmployeeService {
    Employee find(Integer id);

    Employee find(String email);

    void create(Employee employee);

    void edit(Employee employee);

    void fire(Employee employee, Date fireDate);

    Employee info(Employee employee);

    void changeDepartment(Employee employee, Department department);

    Employee leader(Employee employee);
}
