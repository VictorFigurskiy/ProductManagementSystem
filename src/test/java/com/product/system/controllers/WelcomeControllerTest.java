package com.product.system.controllers;

import com.product.system.configuration.MvcConfiguration;
import com.product.system.configuration.SecurityConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by Sonik on 09.09.2017.
 */
@RunWith(value = SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        TestAppModelConfiguration.class,
        MvcConfiguration.class,
        SecurityConfiguration.class})
public class WelcomeControllerTest {




    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void welcome() throws Exception {
    }

    @Test
    public void main() throws Exception {
    }

    @Test
    public void logout() throws Exception {
    }

}