package com.product.system.dao;

import com.product.system.entity.UserEntity;

/**
 * Created by Sonikb on 08.08.2017.
 */
public interface UserDao extends GeneralDao<Integer,UserEntity> {

    UserEntity getByEmail(String email);
}
