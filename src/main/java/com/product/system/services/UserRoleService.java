package com.product.system.services;

import com.product.system.dao.UserRoleDAO;
import com.product.system.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sonikb on 23.08.2017.
 */
@Service
public class UserRoleService {

    private UserRoleDAO userRoleDAO;

    @Autowired
    public UserRoleService(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Transactional(readOnly = true)
    public UserRoleEntity getById(Integer id) {
        return userRoleDAO.getById(UserRoleEntity.class, id);
    }

    @Transactional(readOnly = true)
    public List<UserRoleEntity> getAll() {
        return userRoleDAO.getAll(UserRoleEntity.class);
    }

    @Transactional
    public void save(UserRoleEntity entity) {
        userRoleDAO.save(entity);
    }

    @Transactional
    public void update(UserRoleEntity entity) {
        userRoleDAO.update(entity);
    }

    @Transactional
    public void remove(UserRoleEntity entity) {
        userRoleDAO.remove(entity);
    }
}
