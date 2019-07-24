package com.altarix.task.dao;

import com.altarix.task.model.log.DepartmentLog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class LoggingDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(DepartmentLog departmentLog){
        entityManager.persist(departmentLog);
    }
}
