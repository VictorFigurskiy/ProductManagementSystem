package com.product.system.services;

import com.product.system.dao.UserDao;
import com.product.system.entity.User;
import com.product.system.entity.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Sonik on 11.09.2017.
 */
public class UserDetailedServiceImplTest {

    private UserService userService;
    private UserDetailsService userDetailsService;
    private UserDao userDao;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = mock(User.class);
        when(user.getFirstName()).thenReturn("name");
        when(user.getLastName()).thenReturn("lastName");
        userDao = mock(UserDao.class);
        userService = new UserService(userDao);
        userDetailsService = new UserDetailedServiceImpl(userService);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testUserNotExists() {
        when(userDao.getByEmail("B")).thenReturn(null);
        userDetailsService.loadUserByUsername("B");
    }

    @Test
    public void loadUserByUsername() throws Exception {
        Set<UserRole> roles = mock(Set.class);
        UserRole userRole = mock(UserRole.class);
        when(userRole.getRoleType()).thenReturn("USER");
        roles.add(userRole);

        when(user.getEmail()).thenReturn("email");
        when(user.getPassword()).thenReturn("password");
        when(user.getUserRoles()).thenReturn(roles);
        when(userDao.getByEmail("email")).thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername("email");
        assertEquals("email", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.isAccountNonExpired());

//        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//        assertEquals(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), authorities);
    }

    @Test
    public void testFindOne() {
        when(userDao.getByEmail("A")).thenReturn(user);
        assertEquals(user, userService.getByEmail("A"));
        verify(userDao, times(1)).getByEmail("A");
    }

    @Test
    public void testSave() {
        userService.save(user);
        verify(userDao, times(1)).save(user);
    }
}