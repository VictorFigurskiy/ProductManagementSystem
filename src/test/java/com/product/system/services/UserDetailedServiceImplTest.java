package com.product.system.services;

import com.product.system.dao.UserDao;
import com.product.system.entity.UserEntity;
import com.product.system.entity.UserRoleEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
    private UserEntity userEntity;

    @Before
    public void setUp() throws Exception {
        userEntity = mock(UserEntity.class);
        when(userEntity.getFirstName()).thenReturn("name");
        when(userEntity.getLastName()).thenReturn("lastName");
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
        Set<UserRoleEntity> roles = mock(Set.class);
        UserRoleEntity userRoleEntity = mock(UserRoleEntity.class);
        when(userRoleEntity.getRoleType()).thenReturn("USER");
        roles.add(userRoleEntity);

        when(userEntity.getEmail()).thenReturn("email");
        when(userEntity.getPassword()).thenReturn("password");
        when(userEntity.getUserRoleEntities()).thenReturn(roles);
        when(userDao.getByEmail("email")).thenReturn(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername("email");
        assertEquals("email", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.isAccountNonExpired());

//        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//        assertEquals(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), authorities);
    }

    @Test
    public void testFindOne() {
        when(userDao.getByEmail("A")).thenReturn(userEntity);
        assertEquals(userEntity, userService.getByEmail("A"));
        verify(userDao, times(1)).getByEmail("A");
    }

    @Test
    public void testSave() {
        userService.save(userEntity);
        verify(userDao, times(1)).save(userEntity);
    }
}