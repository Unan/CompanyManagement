package com.altarix.task.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class DepartmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

}
