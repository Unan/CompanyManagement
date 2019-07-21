package com.altarix.task.dao;

import com.altarix.task.model.Department;
import org.springframework.stereotype.Repository;

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

    public void add(Department department) {
        entityManager.persist(department);
    }

    public void delete(Department department) {
        entityManager.remove(department);
    }

    public void update(Department department) {
        entityManager.merge(department);
    }

    public Department find(Integer departmentId) {
        return entityManager.find(Department.class, departmentId);
    }

    public Department find(String departmentName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> departmentRoot = criteriaQuery.from(Department.class);
        criteriaQuery.select(departmentRoot);
        criteriaQuery.where(criteriaBuilder.equal(departmentRoot.get("name"), departmentName));
        List<Department> departments = entityManager.createQuery(criteriaQuery).getResultList();
        if (departments.size() == 0)
            return null;
        else
            return departments.get(0);
    }

    public List<Department> all() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> rootEntry = criteriaQuery.from(Department.class);
        CriteriaQuery<Department> all = criteriaQuery.select(rootEntry);
        TypedQuery<Department> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Department> sub(Department department) {
        if (department != null) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
            Root<Department> departmentRoot = criteriaQuery.from(Department.class);
            criteriaQuery.select(departmentRoot).where(criteriaBuilder.equal(departmentRoot.get("parentDepartment"), department.getId()));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } else return null;
    }
}

