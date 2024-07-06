package org.example.repository;

import org.example.model.Department;
import org.example.model.Employee;
import org.example.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public class EmployeeHibernateDao implements EmployeeDao {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeHibernateDao.class);
    @Autowired
    private SessionFactory sessionFactory;
@Override
    public List<Employee> getEmployees(){
    logger.info("Start to getEmployees from Postgres via Hibernate.");
    List<Employee> employees;
    Session session = sessionFactory.openSession();
    try {
        String hibernageSql = "from Employee";
        Query query = session.createQuery(hibernageSql);
        employees = query.list();
        session.close();
    } catch (HibernateException e) {
        logger.error("Open session exception or close session exception", e);
        session.close();
        throw e;
    }
    logger.info("Get employees {}", employees);
    return employees;

}

}

