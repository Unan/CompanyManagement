package com.altarix.task.service.departmentService;

import com.altarix.task.dao.DepartmentDAO;
import com.altarix.task.model.Department;
import com.altarix.task.model.Employee;
import com.altarix.task.model.dto.DepartmentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO departmentDAO;

    @Value("${nameRegex}")
    private String nameRegex;

    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    private boolean checkDate(Date foundation) {
        return foundation.before(new Date());
    }

    private boolean checkName(String name){
        return name.matches(nameRegex);
    }

    @Override
    public Department generateFromDTO(DepartmentDTO departmentDTO) {
        if (checkDate(departmentDTO.getFoundation()) && checkName(departmentDTO.getName())) {
            Department department = new Department();
            department.setName(departmentDTO.getName());
            department.setFoundation(departmentDTO.getFoundation());
            department.setParentDepartment(find(departmentDTO.getParentDepartment()));
            department.setEmployees(departmentDTO.getEmployees());
            return department;
        } else return null;
    }

    @Override
    public Department find(String name) {
        return departmentDAO.find(name);
    }

    @Override
    public Department find(Integer id) {
        return departmentDAO.find(id);
    }

    @Override
    public void create(Department department) {
        if (department.getId() == null && find(department.getName()) == null) {
            departmentDAO.add(department);
        }
    }

    @Override
    public void changeName(Department department, String requiredName) {
        if (find(department.getId()) != null && find(requiredName) == null) {
            department.setName(requiredName);
            departmentDAO.update(department);
        }
    }

    @Override
    public void delete(Department department) {
        if (find(department.getId()) != null && department.getEmployees().size() == 0) {
            List<Department> subDepartments = departmentDAO.sub(department);
            for (Department d : subDepartments) {
                d.setParentDepartment(department.getParentDepartment());
                departmentDAO.update(d);
            }
            department.setParentDepartment(null);
            departmentDAO.delete(department);
        }
    }

    @Override
    public List<Department> sub(Department department) {
        return departmentDAO.sub(department);
    }

    @Override
    public List<Department> under(Department department) {
        List<Department> underDepartments = new ArrayList<>(departmentDAO.sub(department));
        for (int i = 0; i < underDepartments.size(); i++) {
            underDepartments.addAll(departmentDAO.sub(underDepartments.get(i)));
        }
        return underDepartments;
    }

    @Override
    public void changeParent(Department department, Department requiredParentDepartment) {
        if (find(department.getId()) != null && find(requiredParentDepartment.getId()) != null) {
            if (!under(department).contains(requiredParentDepartment)) {
                department.setParentDepartment(requiredParentDepartment);
                departmentDAO.update(department);
            }
        }
    }

    @Override
    public List<Department> above(Department department) {
        if (department == null || department.getParentDepartment() == null || find(department.getId()) == null) {
            return null;
        } else {
            List<Department> allDepartments = departmentDAO.all();
            Stack<Department> aboveDepartments = new Stack<>();
            aboveDepartments.push(department.getParentDepartment());
            while (allDepartments.size() >= aboveDepartments.size() && aboveDepartments.peek().getParentDepartment() != null) {
                aboveDepartments.push(aboveDepartments.peek().getParentDepartment());
            }
            if (aboveDepartments.size() == allDepartments.size()) {
                return null;
            } else {
                return new ArrayList<>(aboveDepartments);
            }
        }
    }

    @Override
    public Integer employeesWageAmount(Department department) {
        int amountWage = 0;
        if (department != null && department.getEmployees() != null) {
            for (Employee employee : department.getEmployees()) {
                amountWage += employee.getWage();
            }
        }
        return amountWage;
    }
}
