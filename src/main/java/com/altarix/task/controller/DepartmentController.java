package com.altarix.task.controller;

import com.altarix.task.model.Department;
import com.altarix.task.model.dto.DepartmentDTO;
import com.altarix.task.service.departmentService.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public void createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.create(departmentService.generateFromDTO(departmentDTO));
    }

    @GetMapping("/rename/{departmentName},{requiredName}")
    public void update(@PathVariable String departmentName,
                       @PathVariable String requiredName
    ) {
        departmentService.changeParent(departmentService.find(departmentName), departmentService.find(requiredName));
    }

    @GetMapping("/delete/{departmentName}")
    public void delete(@PathVariable String departmentName) {
        departmentService.delete(departmentService.find(departmentName));
    }

    @GetMapping("/get/{departmentId}")
    public Department get(@PathVariable Integer departmentId) {
        return departmentService.find(departmentId);
    }

    @GetMapping("/sub/{departmentName}")
    public List<Department> sub(@PathVariable String departmentName) {
        return departmentService.sub(departmentService.find(departmentName));
    }

    @GetMapping("/under/{departmentName}")
    public List<Department> under(@PathVariable String departmentName) {
        Department department = departmentService.find(departmentName);
        return departmentService.under(department);
    }

    @RequestMapping("/changeParent/{departmentName},{requiredParentName}")
    public void changeParent(@PathVariable String departmentName,
                             @PathVariable String requiredParentName
    ) {
        departmentService.changeParent(find(departmentName), find(requiredParentName));
    }

    @GetMapping("/above/{departmentName}")
    public List<Department> above(@PathVariable String departmentName) {
        Department department = departmentService.find(departmentName);
        return departmentService.above(department);
    }

    @GetMapping("/find/{departmentName}")
    public Department find(@PathVariable String departmentName) {
        return departmentService.find(departmentName);
    }

    @GetMapping("/amountWage/{departmentName}")
    public Integer amountWage(@PathVariable String departmentName) {
        return departmentService.employeesWageAmount(find(departmentName));
    }
}
