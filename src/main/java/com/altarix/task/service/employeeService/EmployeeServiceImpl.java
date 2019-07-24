package com.altarix.task.service.employeeService;

import com.altarix.task.dao.DepartmentDAO;
import com.altarix.task.dao.EmployeeDAO;
import com.altarix.task.model.Department;
import com.altarix.task.model.Employee;
import com.altarix.task.model.Position;
import com.altarix.task.model.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final DepartmentDAO departmentDAO;

    @Value("${nameRegex}")
    private String nameRegex;
    @Value("${emailRegex}")
    private String emailRegex;
    @Value("${phoneRegex}")
    private String phoneRegex;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) {
        this.employeeDAO = employeeDAO;
        this.departmentDAO = departmentDAO;
    }

    private boolean checkName(String name) {
        return name.matches(nameRegex);
    }

    private boolean emailValidation(String email) {
        return email.matches(emailRegex);
    }

    private boolean phoneNumberValidation(String phoneNumber) {
        return phoneNumber.matches(phoneRegex);
    }

    private boolean dateValidation(Date birth, Date hire, Date fire) {
        if (fire == null)
            return birth.before(hire) && hire.before(new Date());
        else
            return birth.before(hire) && hire.before(fire) && fire.before(new Date());
    }

    private boolean wageValidation(EmployeeDTO employeeDTO) {
        boolean head = false;
        try {
            head = find(employeeDTO.getId()).getDepartmentHead();
        } catch (Throwable ignored) {
        }
        if (head) {
            return true;
        } else if (employeeDTO.getDepartment() == null || leader(find(employeeDTO.getEmail())) == null) {
            return true;
        } else if (departmentDAO.find(employeeDTO.getDepartment()) != null
                && departmentDAO.find(employeeDTO.getDepartment()).getEmployees().size() > 0 &&
                leader(find(employeeDTO.getEmail())) != null) {
            return employeeDTO.getWage() <= leader(find(employeeDTO.getEmail())).getWage();
        } else return false;
    }

    private boolean departmentHeadValidation(EmployeeDTO employeeDTO) {
        boolean head = false;
        try {
            head = find(employeeDTO.getId()).getDepartmentHead();
        } catch (Throwable ignored) {
        }
        if (head) {
            return true;
        } else if (employeeDTO.getDepartment() == null || !employeeDTO.getDepartmentHead()) {
            return true;
        } else if (departmentDAO.find(employeeDTO.getDepartment()) != null &&
                departmentDAO.find(employeeDTO.getDepartment()).getEmployees().size() > 0 &&
                leader(find(employeeDTO.getEmail())) != null) {
            return false;
        } else return true;
    }

    @Override
    public Employee generateFromDTO(EmployeeDTO employeeDTO) {
        if (
                phoneNumberValidation(employeeDTO.getPhoneNumber()) &&
                emailValidation(employeeDTO.getEmail()) &&
                dateValidation(employeeDTO.getBirthDate(), employeeDTO.getHireDate(), employeeDTO.getFireDate()) &&
                wageValidation(employeeDTO) &&
                departmentHeadValidation(employeeDTO) &&
                checkName(employeeDTO.getLastName()) &&
                checkName(employeeDTO.getFirstName()) &&
                checkName(employeeDTO.getMiddleName())
        ) {
            Employee employee = new Employee();
            try {
                employee.setId(employeeDTO.getId());
            } catch (Throwable ignored) {
            }
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
        } else return null;
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
        if (employee != null && employee.getId() == null && find(employee.getEmail()) == null) {
            employee.setDepartmentHead(false);
            employeeDAO.add(employee);
        }
    }

    @Override
    public void edit(Employee employee) {
        if (employee != null && find(employee.getId()) != null) {
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
