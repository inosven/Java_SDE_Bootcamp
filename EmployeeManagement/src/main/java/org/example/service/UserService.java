package org.example.service;

import org.example.model.User;
import org.example.repository.UserDaoImpl;
import org.example.service.exceptions.MissingUserException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.HibernateException;
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDaoImpl userDao;
@Autowired
private SessionFactory sessionFactory;

//    @Override
    public User getUserByCredentials(String email, String password) throws Exception{
        User user = userDao.getUserByCredentials(email, password);
        if (user == null) {
            throw new MissingUserException("No user with email " + email + " found");
        }
        return user;
    }

//    @Override
    public User getUserbyId(Long id) {
        Session s = sessionFactory.openSession();
        String hql = "FROM User d where id= :Id";
        try {
            Query<User> query = s.createQuery(hql);
            query.setParameter("Id", id);
            User result = query.uniqueResult();
            s.close();
            return result;
        } catch (HibernateException e) {
            logger.error("Session close exception try again", e);
            s.close();
            return null;
        }
    }

}
