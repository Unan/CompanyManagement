package com.altarix.task.service.employeeService;

import com.altarix.task.dao.DepartmentDAO;
import com.altarix.task.dao.EmployeeDAO;
import com.altarix.task.model.Department;
import com.altarix.task.model.Employee;
import com.altarix.task.model.Position;
import com.altarix.task.model.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final DepartmentDAO departmentDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) {
        this.employeeDAO = employeeDAO;
        this.departmentDAO = departmentDAO;
    }

    @Override
    public Employee generateFromDTO(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        try {employee.setId(employeeDTO.getId());} catch (Throwable ignored){}
        employee.setEmail(employeeDTO.getEmail());
        employee.setLastName(employeeDTO.getLastName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setGender(employeeDTO.getGender());
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setFireDate(employeeDTO.getFireDate());
        employee.setPosition(employeeDTO.getPosition());
        employee.setWage(employeeDTO.getWage());
        employee.setDepartment(departmentDAO.find(employeeDTO.getDepartment()));
        employee.setDepartmentHead(employeeDTO.getDepartmentHead());
        return employee;
    }

    @Override
    public Employee find(Integer id) {
        return employeeDAO.find(id);
    }

    @Override
    public Employee find(String email) {
        return employeeDAO.find(email);
    }

    @Override
    public List<Employee> employeesOnPosition(Position position) {
        return employeeDAO.employeesOnPosition(position);
    }

    @Override
    public void create(Employee employee) {
        if (employee.getId() == null && find(employee.getEmail()) == null) {
            employee.setDepartmentHead(false);
            employeeDAO.add(employee);
        }
    }

    @Override
    public void edit(Employee employee) {
        if (find(employee.getId()) != null) {
            employeeDAO.update(employee);
        }
    }

    @Override
    public void fire(Employee employee, Date fireDate) {
        if (employee != null && fireDate.before(new Date())) {
            Department department = new Department();
            department = employee.getDepartment();
            employee.setDepartmentHead(false);
            employee.setDepartment(null);
            employee.setWage(0);
            employee.setFireDate(fireDate);
            employeeDAO.update(employee);
            department.getEmployees().remove(employee);
            departmentDAO.update(department);
        }
    }

    @Override
    public void changeDepartment(Employee employee, Department requiredDepartment) {
        if (employee != null && requiredDepartment != null) {
            Department previousDepartment = new Department();
            previousDepartment = employee.getDepartment();

            employee.setDepartmentHead(false);
            employee.setDepartment(null);
            employeeDAO.update(employee);

            previousDepartment.getEmployees().remove(employee);
            departmentDAO.update(previousDepartment);

            employee.setDepartment(requiredDepartment);
            requiredDepartment.getEmployees().add(employee);
            departmentDAO.update(requiredDepartment);
        }
    }

    @Override
    public Employee leader(Employee employee) {
        for (Employee e : employee.getDepartment().getEmployees())
            if (e.getDepartmentHead())
                return e;
        return null;
    }

    @Override
    public void moveDepartmentEmployeesToDepartment(Department currentDepartment, Department requiredDepartment) {
        List<Employee> employees = new ArrayList<>(currentDepartment.getEmployees());
        for (Employee employee : employees) {
            employee.setDepartmentHead(false);
            employee.setDepartment(null);
        }
        for (Employee employee : employees) {
            employeeDAO.update(employee);
        }
        currentDepartment.getEmployees().removeAll(currentDepartment.getEmployees());
        departmentDAO.update(currentDepartment);

        for (Employee employee : employees) {
            employee.setDepartment(requiredDepartment);
        }
        requiredDepartment.getEmployees().addAll(employees);
        departmentDAO.update(requiredDepartment);
    }
}
