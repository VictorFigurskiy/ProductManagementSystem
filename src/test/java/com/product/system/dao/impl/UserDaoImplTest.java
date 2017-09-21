package com.product.system.dao.impl;

import com.product.system.dao.UserDao;
import com.product.system.dao.impl.config.TestConfiguration;
import com.product.system.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Sonik on 08.09.2017.
 */
@RunWith(value = SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class UserDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private UserDao userDao;
    private UserEntity userEntity;


    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        userDao = new UserDaoImpl(sessionFactory);
        userEntity = mock(UserEntity.class);
    }

    @Test
    public void getByEmail() throws Exception {
        when(userEntity.getEmail()).thenReturn("email");

        Query<UserEntity> query = mock(Query.class);
        when(session.createQuery("select user from UserEntity user where user.email = :email", UserEntity.class)).thenReturn(query);
        Query<UserEntity> userQuery = mock(Query.class);
        when(query.setParameter("email", "email")).thenReturn(userQuery);
        when(userQuery.getSingleResult()).thenReturn(userEntity);

        assertEquals(userEntity, userDao.getByEmail("email"));
        verify(userQuery, atLeast(1)).getSingleResult();
    }

}