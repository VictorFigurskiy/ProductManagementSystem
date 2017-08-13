package com.product.system.dao.impl;

import com.product.system.dao.AbstractGeneralDaoImpl;
import com.product.system.dao.UserDao;
import com.product.system.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Sonikb on 08.08.2017.
 */
@Repository
public class UserDaoImpl extends AbstractGeneralDaoImpl<Integer,User> implements UserDao {

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
