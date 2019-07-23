package com.altarix.task.controller;

import com.altarix.task.model.Employee;
import com.altarix.task.model.Position;
import com.altarix.task.model.dto.EmployeeDTO;
import com.altarix.task.service.departmentService.DepartmentService;
import com.altarix.task.service.employeeService.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee")
@Api(value = "Employee Management System")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @ApiOperation(value = "Get all employees of department", response = List.class)
    @GetMapping("departmentEmployees/{departmentName}")
    public List<Employee> departmentEmployees(@PathVariable String departmentName) {
        return departmentService.find(departmentName).getEmployees();
    }

    @ApiOperation(value = "Create new employee")
    @PostMapping("/add")
    public void add(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeService.generateFromDTO(employeeDTO));
    }

    @ApiOperation(value = "Edit employees info")
    @PutMapping("/edit")
    public void edit(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.edit(employeeService.generateFromDTO(employeeDTO));
    }

    @ApiOperation(value = "Fire employee with date")
    @PutMapping("/fire/{email},{fireDate}")
    public void fire(@PathVariable String email,
                     @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fireDate
    ) {
        employeeService.fire(employeeService.find(email), fireDate);
    }

    @ApiOperation(value = "Get employees info by email or id", response = Employee.class)
    @GetMapping("/info/{emailOrId}")
    public Employee info(@PathVariable String emailOrId) {
        if (emailOrId.contains("@")) {
            return employeeService.find(emailOrId);
        } else
            return employeeService.find(Integer.valueOf(emailOrId));
    }

    @ApiOperation(value = "Move employee from one department to another")
    @PutMapping("/changeDepartment/{employeeEmail},{departmentName}")
    public void changeDepartment(@PathVariable String employeeEmail,
                                 @PathVariable String departmentName
    ) {
        employeeService.changeDepartment(employeeService.find(employeeEmail), departmentService.find(departmentName));
    }

    @ApiOperation(value = "Move all employees of department to another department")
    @PutMapping("/moveDepartmentEmployeesToDepartment/{currentDepartmentName},{requiredDepartmentName}")
    public void moveDepartmentEmployeesToDepartment(@PathVariable String currentDepartmentName,
                                                    @PathVariable String requiredDepartmentName
    ) {
        employeeService.moveDepartmentEmployeesToDepartment(
                departmentService.find(currentDepartmentName), departmentService.find(requiredDepartmentName));
    }

    @ApiOperation(value = "Get employees leader", response = Employee.class)
    @GetMapping("/employeesLeader/{email}")
    public Employee employeesLeader(@PathVariable String email) {
        return employeeService.leader(employeeService.find(email));
    }

    @ApiOperation(value = "Find all employees on position", response = List.class)
    @GetMapping("/employeesOnPosition/{position}")
    public List<Employee> employeesOnPosition(@PathVariable String position) {
        return employeeService.employeesOnPosition(Position.valueOf(position));
    }
}
