package com.product.system.services;

import com.product.system.dao.UserRoleDAO;
import com.product.system.entity.UserRole;
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
    public UserRole getById(Integer id) {
        return userRoleDAO.getById(UserRole.class, id);
    }

    @Transactional(readOnly = true)
    public List<UserRole> getAll() {
        return userRoleDAO.getAll(UserRole.class);
    }

    @Transactional
    public void save(UserRole entity) {
        userRoleDAO.save(entity);
    }

    @Transactional
    public void update(UserRole entity) {
        userRoleDAO.update(entity);
    }

    @Transactional
    public void remove(UserRole entity) {
        userRoleDAO.remove(entity);
    }
}
