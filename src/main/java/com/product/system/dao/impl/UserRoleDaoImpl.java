package com.product.system.dao.impl;

import com.product.system.dao.UserRoleDAO;
import com.product.system.entity.UserRoleEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Sonikb on 23.08.2017.
 */
@Repository
public class UserRoleDaoImpl extends AbstractGeneralDaoImpl<Integer,UserRoleEntity> implements UserRoleDAO {

    @Autowired
    public UserRoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
