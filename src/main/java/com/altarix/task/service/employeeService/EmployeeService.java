package com.altarix.task.service.employeeService;

import com.altarix.task.model.Department;
import com.altarix.task.model.Employee;
import com.altarix.task.model.Position;
import com.altarix.task.model.dto.EmployeeDTO;

import java.util.Date;
import java.util.List;

public interface EmployeeService {

    Employee generateFromDTO(EmployeeDTO employeeDTO);

    Employee find(Integer id);

    Employee find(String email);

    List<Employee> employeesOnPosition(Position position);

    void create(Employee employee);

    void edit(Employee employee);

    void fire(Employee employee, Date fireDate);

    void changeDepartment(Employee employee, Department requiredDepartment);

    Employee leader(Employee employee);

    void moveDepartmentEmployeesToDepartment(Department currentDepartment, Department requiredDepartment);
}
