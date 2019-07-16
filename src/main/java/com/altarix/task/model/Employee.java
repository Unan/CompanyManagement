package com.altarix.task.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Employee {

    @Id
    private String email;
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;

    private String middleName;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    private Date birthDate;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Date hireDate;

    private Date fireDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Position position;
    @NotNull
    private Integer wage;
    @NotNull
    @ManyToOne
    private Department department;
    @NotNull
    private Boolean departmentHead;

    public Employee() {
    }

    public Employee(String email,
                    String lastName,
                    String firstName,
                    String middleName,
                    Gender gender,
                    Date birthDate,
                    String phoneNumber,
                    Date hireDate,
                    Date fireDate,
                    Position position,
                    Integer wage,
                    Department department,
                    Boolean departmentHead
    ) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.fireDate = fireDate;
        this.position = position;
        this.wage = wage;
        this.department = department;
        this.departmentHead = departmentHead;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getFireDate() {
        return fireDate;
    }

    public void setFireDate(Date fireDate) {
        this.fireDate = fireDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getWage() {
        return wage;
    }

    public void setWage(Integer wage) {
        this.wage = wage;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(Boolean departmentHead) {
        this.departmentHead = departmentHead;
    }
}
