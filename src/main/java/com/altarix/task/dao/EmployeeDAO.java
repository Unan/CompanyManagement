package com.altarix.task.dao;

import com.altarix.task.model.Employee;
import com.altarix.task.model.Position;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Employee employee) {
        entityManager.persist(employee);
    }

    public void delete(Integer employeeId) {
        entityManager.remove(find(employeeId));
    }

    public void update(Employee employee) {
        entityManager.merge(employee);
    }

    public Employee find(Integer employeeId) {
        try {
            return entityManager.find(Employee.class, employeeId);
        } catch (Throwable throwable) {
            return null;
        }
    }

    public Employee find(String employeeEmail) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employeeRoot);
        criteriaQuery.where(criteriaBuilder.equal(employeeRoot.get("email"), employeeEmail));
        List<Employee> employees = entityManager.createQuery(criteriaQuery).getResultList();
        if (employees.size() == 0)
            return null;
        else
            return employees.get(0);
    }

    public List<Employee> employeesOnPosition(Position position) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employeeRoot).where(criteriaBuilder.equal(employeeRoot.get("position"), position));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
