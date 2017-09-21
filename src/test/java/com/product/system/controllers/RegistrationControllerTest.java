package com.product.system.controllers;

import com.product.system.configuration.MvcConfiguration;
import com.product.system.configuration.SecurityConfiguration;
import com.product.system.controllers.config.TestAppModelConfiguration;

import com.product.system.entity.UserEntity;
import com.product.system.entity.UserRoleEntity;
import com.product.system.services.UserRoleService;
import com.product.system.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Sonik on 11.09.2017.
 */
@RunWith(value = SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        TestAppModelConfiguration.class,
        MvcConfiguration.class,
        SecurityConfiguration.class})
public class RegistrationControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    private UserEntity userEntity;
    private UserRoleEntity userRoleEntity;

    @Before
    public void setUp() throws Exception {
        userEntity = mock(UserEntity.class);
        userRoleEntity = mock(UserRoleEntity.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void register() throws Exception {
        when(userRoleService.getById(1)).thenReturn(userRoleEntity);
        assertEquals(userRoleEntity, userRoleService.getById(1));

        HashSet<UserRoleEntity> userRoleEntities = new HashSet<>();
        userRoleEntities.add(userRoleEntity);
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUserRoleEntities(userRoleEntities);

        doAnswer(invocation -> null).when(userService).save(newUserEntity);

        userService.save(newUserEntity);

        verify(userService).save(newUserEntity);

        mvc.perform(MockMvcRequestBuilders.post("/register").with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER")))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"))
                .andExpect(MockMvcResultMatchers.status().isFound());

    }

}