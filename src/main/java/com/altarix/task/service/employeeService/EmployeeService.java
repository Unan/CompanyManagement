package com.altarix.task.service.employeeService;

import com.altarix.task.model.Employee;

import java.util.Date;

public interface EmployeeService {
    void createEmployee(Employee employee);

    void editEmployeeInfo(Employee currentEmployee, Employee editedEmployee);

    void fireEmployee(String employeeEmail, Date fireDate);

    Employee employeeInfo(String employeeEmail);

    void changeEmployeeDepartment(String employeeEmail, String departmentName);

    Employee employeeLeader(String employeeEmail);

    Employee searchEmployee(String employeeFirstName);
}
