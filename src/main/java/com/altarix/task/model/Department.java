package com.altarix.task.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Department {

    @Id
    private String name;
    @NotNull
    private Date foundation;
    @ManyToOne
    private Department parentDepartment;
    @OneToMany
    private List<Department> subDepartments;
    @OneToMany
    private List<Employee> employees;
    @OneToOne
    private Employee head;

    public Department() {
    }

    public Department(String name,
                      Date foundation,
                      Department parentDepartment,
                      List<Department> subDepartments,
                      List<Employee> employees,
                      Employee head
    ) {
        this.name = name;
        this.foundation = foundation;
        this.parentDepartment = parentDepartment;
        this.subDepartments = subDepartments;
        this.employees = employees;
        this.head = head;
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

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public List<Department> getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(List<Department> subDepartments) {
        this.subDepartments = subDepartments;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }
}
