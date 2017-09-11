package com.product.system.dao.impl;

import com.product.system.dao.UserRoleDAO;
import com.product.system.dao.impl.config.TestConfiguration;
import com.product.system.entity.UserRole;
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
    private UserRole userRole;

    @Before
    public void setUp() throws Exception {
        session = mock(Session.class);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        userRoleDAO = new UserRoleDaoImpl(sessionFactory);
        userRole = mock(UserRole.class);
    }

    @Test
    public void sessionTest(){
        when(userRole.getRoleType()).thenReturn(UserRoleType.ADMIN.name());
        when(session.get(UserRole.class, 1)).thenReturn(userRole);

        assertEquals(userRole, userRoleDAO.getById(UserRole.class,1));
        verify(session, timeout(100)).get(UserRole.class, 1);

        assertEquals("ADMIN", userRole.getRoleType());
    }

    @Test
    public void testNull(){
        when(session.get(UserRole.class, 1)).thenReturn(null);

        assertNull(userRoleDAO.getById(UserRole.class, 1));
        verify(session, timeout(100)).get(UserRole.class, 1);
    }

    @Test(expected = Exception.class)
    public void testException(){
        when(session.get(UserRole.class, -100)).thenThrow(new Exception());

        userRoleDAO.getById(UserRole.class, -100);
    }
}