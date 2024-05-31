package org.example.repository;

import org.example.model.Department;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
            logger.error("Open session exception or close session exception",e);
            session.close();
            throw e;
        }
        logger.info("Get departments {}", departments);
        return departments;
    }
}
