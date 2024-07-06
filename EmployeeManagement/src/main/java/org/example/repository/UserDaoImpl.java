package org.example.repository;

import org.example.model.User;
import org.example.repository.exceptions.UserNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
@Service
public class UserDaoImpl implements IUserDao{
    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public User getUserByCredentials(String email, String password) throws HibernateException {
        String hql = "from User as u where lower(u.email) = :email and u.password = :password";
        logger.info("User email: %, user password: %", email, password);
        try{
            Session session = sessionFactory.openSession();
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password", password);
            return query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("error: {}",e.getMessage());
            throw new UserNotFoundException("can't find user record with email: " + email );
        }
    }
}
