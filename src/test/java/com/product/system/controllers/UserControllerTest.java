package com.product.system.controllers;

import com.product.system.configuration.MvcConfiguration;
import com.product.system.configuration.SecurityConfiguration;
import com.product.system.controllers.config.TestAppModelConfiguration;
import com.product.system.entity.Product;
import com.product.system.entity.User;
import com.product.system.services.UserService;
import org.hamcrest.CoreMatchers;
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

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * Created by Sonik on 11.09.2017.
 */
@RunWith(value = SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        TestAppModelConfiguration.class,
        MvcConfiguration.class,
        SecurityConfiguration.class})
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = mock(User.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void userList() throws Exception {
        when(userService.getAll()).thenReturn(Collections.singletonList(user));

        mvc.perform(MockMvcRequestBuilders.get("/user/list").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("userList", CoreMatchers.equalTo(Collections.singletonList(user))))
                .andExpect(MockMvcResultMatchers.view().name("users"));
    }

    @Test
    public void delete() throws Exception {
        when(userService.getById(1)).thenReturn(user);
        assertEquals(user, userService.getById(1));
        verify(userService, times(1)).getById(1);

        doAnswer(invocation -> null).when(userService).remove(user);

        userService.remove(user);

        verify(userService, atMost(1)).remove(user);

        User otherUser = mock(User.class);
        when(userService.getAll()).thenReturn(Collections.singletonList(otherUser));

        mvc.perform(MockMvcRequestBuilders.get("/user/delete1").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("userList", CoreMatchers.equalTo(Collections.singletonList(otherUser))))
                .andExpect(MockMvcResultMatchers.view().name("users"));
    }

}