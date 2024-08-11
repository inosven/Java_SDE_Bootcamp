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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class DepartmentHibernateDao implements DepartmentDao {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentHibernateDao.class);

    @Autowired
    private SessionFactory sessionFactory;

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

//    @Override
//    public Department update(String oldName, String name, String description, String location) throws SQLException {
//        logger.info("Start to update a record by id from Postgres via Hibernate.");
//        Department department;
//        Session session = sessionFactory.openSession();
//        try {
//            department = session.get(Department.class, oldName);
//            department.setName(name);
//            department.setDescription(description);
//            department.setLocation(location);
//        }catch (Exception e) {
//            logger.error("Error when delete a department record", e);
//            throw e;
//        }
//        return null;
//    }

    @Override
    public void delete(Department department) throws SQLException {
        logger.info("Start to delete a record by id from Postgres via Hibernate.");
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(department);
            logger.info("Deleted a record by id from Postgres via Hibernate.");
            transaction.commit();
            session.close();
        } catch (Exception e) {
            logger.error("Error when delete a department record", e);
            throw e;
        }
    }

    @Override
    public Department getDepartmentEagerBy(Long id) {
        Transaction transaction = null;

        String hql = "FROM Department d LEFT JOIN FETCH d.employees where d.id = :Id";
        Session session = sessionFactory.openSession();
        try {
            transaction.begin();
            Query<Department> query = session.createQuery(hql);
            query.setParameter("Id", id);
            Department department = query.uniqueResult();
            session.close();
            logger.info("Get department by id using eager fetch from Postgres via Hibernate.");
            transaction.commit();
            return department;
        } catch (HibernateException e) {
            logger.error("failed to retrieve data record", e);
            session.close();
            return null;
        }
    }

    @Override
    public void create(Department department) throws SQLException {
        logger.info("Start to create a record from Postgres via Hibernate.");
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

    @Override
    public Department update(Department department) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
            Department d = getDepartmentById(department.getId());
            session.close();
            return d;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            logger.error("failed to insert record", e);
            session.close();
            return null;
        }
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
    public Department getDepartmentById(Long id) {
        logger.info("Fetching department from database by ID: {}", id);
        Department department = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            department = session.get(Department.class, id);
            if (department == null) {
                logger.warn("No department found with ID: {}", id);
            } else {
                logger.info("Fetched department: {}", department);
            }
        } catch (HibernateException e) {
            logger.error("Error retrieving department by ID: {}", id, e);
            throw e; // You may handle or wrap this exception as needed
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    logger.error("Error closing session", e);
                }
            }
        }
        return department;
    }

}



