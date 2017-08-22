package com.product.system.dao.impl;

import com.product.system.dao.UserDao;
import com.product.system.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Sonikb on 08.08.2017.
 */
@Repository
public class UserDaoImpl extends AbstractGeneralDaoImpl<Integer, User> implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select user from User user where user.email = :email", User.class).setParameter("email", email).getSingleResult();
    }
}
