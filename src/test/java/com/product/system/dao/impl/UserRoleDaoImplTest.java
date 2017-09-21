package com.product.system.dao.impl;

import com.product.system.dao.UserRoleDAO;
import com.product.system.dao.impl.config.TestConfiguration;
import com.product.system.entity.UserRoleEntity;
import com.product.system.entity.UserRoleType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class UserRoleDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private UserRoleDAO userRoleDAO;
    private UserRoleEntity userRoleEntity;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        userRoleDAO = new UserRoleDaoImpl(sessionFactory);
        userRoleEntity = mock(UserRoleEntity.class);
    }

    @Test
    public void sessionTest(){
        when(userRoleEntity.getRoleType()).thenReturn(UserRoleType.ADMIN.name());
        when(session.get(UserRoleEntity.class, 1)).thenReturn(userRoleEntity);

        assertEquals(userRoleEntity, userRoleDAO.getById(UserRoleEntity.class,1));
        verify(session, timeout(100)).get(UserRoleEntity.class, 1);

        assertEquals("ADMIN", userRoleEntity.getRoleType());
    }

    @Test
    public void testNull(){
        when(session.get(UserRoleEntity.class, 1)).thenReturn(null);

        assertNull(userRoleDAO.getById(UserRoleEntity.class, 1));
        verify(session, timeout(100)).get(UserRoleEntity.class, 1);
    }

    @Test(expected = Exception.class)
    public void testException(){
        when(session.get(UserRoleEntity.class, -100)).thenThrow(new Exception());

        userRoleDAO.getById(UserRoleEntity.class, -100);
    }
}