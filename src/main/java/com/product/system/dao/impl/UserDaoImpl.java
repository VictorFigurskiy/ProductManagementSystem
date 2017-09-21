package com.product.system.dao.impl;

import com.product.system.dao.UserDao;
import com.product.system.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Sonikb on 08.08.2017.
 */
@Repository
public class UserDaoImpl extends AbstractGeneralDaoImpl<Integer, UserEntity> implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserEntity getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select user from UserEntity user where user.email = :email", UserEntity.class).setParameter("email", email).getSingleResult();
    }
}
