package com.altarix.task.model.log;

import javax.persistence.*;

@Entity
public class DepartmentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private DepartmentAction departmentAction;

    private String previousState;

    private String currentState;

    public DepartmentLog() {
    }

    public DepartmentLog(DepartmentAction departmentAction, String previousState, String currentState) {
        this.departmentAction = departmentAction;
        this.previousState = previousState;
        this.currentState = currentState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartmentAction getDepartmentAction() {
        return departmentAction;
    }

    public void setDepartmentAction(DepartmentAction departmentAction) {
        this.departmentAction = departmentAction;
    }

    public String getPreviousState() {
        return previousState;
    }

    public void setPreviousState(String previousState) {
        this.previousState = previousState;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
}
