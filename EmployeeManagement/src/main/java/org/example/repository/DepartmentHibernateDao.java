package org.example.repository;

import org.example.model.Department;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class DepartmentHibernateDao implements DepartmentDao {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentHibernateDao.class);
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Department> getDepartments() throws SQLException {
        logger.info("Start to getDepartments from Postgres via Hibernate.");
        List<Department> departments;
        Session session = sessionFactory.openSession();
        try {
            String hibernageSql = "from Department";
            Query query = session.createQuery(hibernageSql);
            departments = query.list();
            session.close();
        } catch (HibernateException e) {
            logger.error("Open session exception or close session exception", e);
            session.close();
            throw e;
        }
        logger.info("Get departments {}", departments);
        return departments;
    }

    @Override
    public Department update(Long id, String name, String description, String location) throws SQLException {
        logger.info("Start to update a record by id from Postgres via Hibernate.");
        Department department;
        Session session = sessionFactory.openSession();
        try {
            department = session.get(Department.class, id);
            department.setName(name);
            department.setDescription(description);
            department.setLocation(location);
        }catch (Exception e) {
            logger.error("Error when delete a department record", e);
            throw e;
        }
        return null;
    }

    @Override
    public void deleteByName(String name) throws SQLException {
        logger.info("Start to delete a record by id from Postgres via Hibernate.");
        Department department;
        Session session = sessionFactory.openSession();
        try {
            department = session.get(Department.class, name);
            session.delete(department);
            session.close();
        } catch (Exception e) {
            logger.error("Error when delete a department record", e);
            throw e;
        }
    }

    @Override
    public void create(String name, String description, String location) throws SQLException {
        logger.info("Start to create a record from Postgres via Hibernate.");
        Department department = new Department();
        department.setName(name);
        department.setDescription(description);
        department.setLocation(location);
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                logger.error("Error when insert a department record", e);
            }
            session.close();
            throw e;
        }
        logger.info("Save departments {}", department);
    }

    public Department getDepartmentsByName(String departmentName) throws SQLException {
        logger.info("Start to getDepartments from Postgres by Name via Hibernate.");
        List<Department> departments;
        Department department = new Department();
        Session session = sessionFactory.openSession();
        try {
            String hibernageSql = "from Department as department where department.name = :departmentName";
            Query query = session.createQuery(hibernageSql);
            query.setParameter("departmentName", departmentName);
            query.executeUpdate();
            departments = query.list();
            department = departments.get(0);
            session.close();
        } catch (HibernateException e) {
            logger.error("Open session exception or close session exception", e);
            throw e;
        }
        logger.info("Get departments {}", departments);
        return department;

    }

    @Override
    public Department create(Long id, String name, String description, String location) throws SQLException {
        return null;
    }
}
