package com.altarix.task.dao;

import com.altarix.task.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

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
        return entityManager.find(Employee.class, employeeId);
    }

    public Employee find(String employeeEmail) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employeeRoot);
        criteriaQuery.where(criteriaBuilder.equal(employeeRoot.get("email"), employeeEmail));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
