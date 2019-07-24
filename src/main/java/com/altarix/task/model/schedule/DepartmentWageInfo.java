package com.altarix.task.model.schedule;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DepartmentWageInfo {

    @Id
    private Integer id;

    private String departmentName;

    private Integer wageAmount;

    public DepartmentWageInfo() {
    }

    public DepartmentWageInfo(Integer id, String departmentName, Integer wageAmount) {
        this.id = id;
        this.departmentName = departmentName;
        this.wageAmount = wageAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getWageAmount() {
        return wageAmount;
    }

    public void setWageAmount(Integer wageAmount) {
        this.wageAmount = wageAmount;
    }
}
