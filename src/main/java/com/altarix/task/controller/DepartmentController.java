package com.altarix.task.controller;

import com.altarix.task.model.Department;
import com.altarix.task.model.dto.DepartmentDTO;
import com.altarix.task.service.departmentService.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@Api(value = "Department Management System")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiOperation(value = "Create new department")
    @PostMapping("/create")
    public void create(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.create(departmentService.generateFromDTO(departmentDTO));
    }

    @ApiOperation(value = "Change department name")
    @PutMapping("/rename/{departmentName},{requiredName}")
    public void rename(@PathVariable String departmentName,
                       @PathVariable String requiredName
    ) {
        departmentService.changeName(departmentService.find(departmentName), requiredName);
    }

    @ApiOperation(value = "Delete department if there's no employee in it")
    @DeleteMapping("/delete/{departmentName}")
    public void delete(@PathVariable String departmentName) {
        departmentService.delete(departmentService.find(departmentName));
    }

    @ApiOperation(value = "Find department by id", response = Department.class)
    @GetMapping("/get/{departmentId}")
    public Department get(@PathVariable Integer departmentId) {
        return departmentService.find(departmentId);
    }

    @ApiOperation(value = "Get departments that are directly subordinate", response = List.class)
    @GetMapping("/sub/{departmentName}")
    public List<Department> sub(@PathVariable String departmentName) {
        return departmentService.sub(departmentService.find(departmentName));
    }

    @ApiOperation(value = "Get departments that are lower in hierarchy", response = List.class)
    @GetMapping("/under/{departmentName}")
    public List<Department> under(@PathVariable String departmentName) {
        Department department = departmentService.find(departmentName);
        return departmentService.under(department);
    }

    @ApiOperation(value = "Change department parent")
    @PutMapping("/changeParent/{departmentName},{requiredParentName}")
    public void changeParent(@PathVariable String departmentName,
                             @PathVariable String requiredParentName
    ) {
        departmentService.changeParent(departmentService.find(departmentName), departmentService.find(requiredParentName));
    }

    @ApiOperation(value = "Get all departments that higher in hierarchy", response = List.class)
    @GetMapping("/above/{departmentName}")
    public List<Department> above(@PathVariable String departmentName) {
        Department department = departmentService.find(departmentName);
        return departmentService.above(department);
    }

    @ApiOperation(value = "Find department by name", response = Department.class)
    @GetMapping("/find/{departmentName}")
    public Department find(@PathVariable String departmentName) {
        return departmentService.find(departmentName);
    }

    @ApiOperation(value = "Get amount of all employees wages", response = Integer.class)
    @GetMapping("/amountWage/{departmentName}")
    public Integer amountWage(@PathVariable String departmentName) {
        return departmentService.employeesWageAmount(find(departmentName));
    }
}
