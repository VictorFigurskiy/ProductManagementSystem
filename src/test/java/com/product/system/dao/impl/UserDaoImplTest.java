package com.product.system.dao.impl;

import com.product.system.dao.UserDao;
import com.product.system.entity.User;
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
    private User user;


    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        userDao = new UserDaoImpl(sessionFactory);
        user = mock(User.class);
    }

    @Test
    public void getByEmail() throws Exception {
        when(user.getEmail()).thenReturn("email");

        Query<User> query = mock(Query.class);
        when(session.createQuery("select user from User user where user.email = :email", User.class)).thenReturn(query);
        Query<User> userQuery = mock(Query.class);
        when(query.setParameter("email", "email")).thenReturn(userQuery);
        when(userQuery.getSingleResult()).thenReturn(user);

        assertEquals(user, userDao.getByEmail("email"));
        verify(userQuery, atLeast(1)).getSingleResult();
    }

}