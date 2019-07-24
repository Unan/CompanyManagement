package com.altarix.task.model.dto;

import com.altarix.task.model.Employee;

import java.util.Date;
import java.util.List;

public class DepartmentDTO {

    private String name;

    private Date foundation;

    private String parentDepartment;

    private List<Employee> employees;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String name,
                         Date foundation,
                         String parentDepartment,
                         List<Employee> employees
    ) {
        this.name = name;
        this.foundation = foundation;
        this.parentDepartment = parentDepartment;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundation() {
        return foundation;
    }

    public void setFoundation(Date foundation) {
        this.foundation = foundation;
    }

    public String getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(String parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
