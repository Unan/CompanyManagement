package com.altarix.task.controller;

import com.altarix.task.model.Employee;
import com.altarix.task.model.Position;
import com.altarix.task.model.dto.EmployeeDTO;
import com.altarix.task.service.departmentService.DepartmentService;
import com.altarix.task.service.employeeService.EmployeeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("departmentEmployees/{departmentName}")
    public List<Employee> departmentEmployees(@PathVariable String departmentName) {
        return departmentService.find(departmentName).getEmployees();
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeService.generateFromDTO(employeeDTO));
    }

    @PostMapping("/edit")
    public void edit(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.edit(employeeService.generateFromDTO(employeeDTO));
    }

    @GetMapping("/fire/{email},{fireDate}")
    public void fire(@PathVariable String email,
                     @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fireDate
    ) {
        employeeService.fire(employeeService.find(email), fireDate);
    }

    @GetMapping("/info/{emailOrId}")
    public Employee info(@PathVariable String emailOrId) {
        if (emailOrId.contains("@")) {
            return employeeService.find(emailOrId);
        } else
            return employeeService.find(Integer.valueOf(emailOrId));
    }

    @GetMapping("/changeDepartment/{employeeEmail},{departmentName}")
    public void changeDepartment(@PathVariable String employeeEmail,
                                 @PathVariable String departmentName
    ) {
        employeeService.changeDepartment(employeeService.find(employeeEmail), departmentService.find(departmentName));
    }

    @GetMapping("/moveDepartmentEmployeesToDepartment/{currentDepartmentName},{requiredDepartmentName}")
    public void moveDepartmentEmployeesToDepartment(@PathVariable String currentDepartmentName,
                                                    @PathVariable String requiredDepartmentName
    ) {
        employeeService.moveDepartmentEmployeesToDepartment(
                departmentService.find(currentDepartmentName), departmentService.find(requiredDepartmentName));
    }

    @GetMapping("/employeesLeader/{email}")
    public Employee employeesLeader(@PathVariable String email) {
        return employeeService.leader(employeeService.find(email));
    }

    @GetMapping("/employeesOnPosition/{position}")
    public List<Employee> onPosition(@PathVariable String position) {
        return employeeService.employeesOnPosition(Position.valueOf(position));
    }
}
