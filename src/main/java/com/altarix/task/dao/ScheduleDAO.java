package com.altarix.task.dao;

import com.altarix.task.model.DepartmentWageInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ScheduleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void update(DepartmentWageInfo departmentWageInfo){
        entityManager.merge(departmentWageInfo);
    }
}
