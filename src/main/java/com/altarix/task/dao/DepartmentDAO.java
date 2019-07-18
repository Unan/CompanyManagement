package com.altarix.task.dao;

import com.altarix.task.model.Department;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DepartmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void addDepartment(Department department) {
        entityManager.persist(department);
    }

    public boolean departmentExistence(String departmentName) {
        return entityManager.contains(findDepartmentByName(departmentName));
    }

    public Department findDepartmentByName(String departmentName) {
        return entityManager.find(Department.class, departmentName);
    }

    public void deleteDepartment(String departmentName){
        entityManager.remove(findDepartmentByName(departmentName));
    }

    public List<Department> allDepartments() {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Department> cq = cb.createQuery(Department.class);
        Root<Department> rootEntry = cq.from(Department.class);
        CriteriaQuery<Department> all = cq.select(rootEntry);
        TypedQuery<Department> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Department> getSubDepartments(String parentDepartment){
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Department.class);
        criteria.add(Restrictions.gt("department_name", parentDepartment));
        System.out.println(session.createSQLQuery("SELECT FROM department WHERE parent_department_name LIKE " + parentDepartment));
        return null;
    }
}

