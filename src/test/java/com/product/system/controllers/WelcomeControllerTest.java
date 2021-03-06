package com.product.system.controllers;

import com.product.system.configuration.MvcConfiguration;
import com.product.system.configuration.SecurityConfiguration;
import com.product.system.controllers.config.TestAppModelConfiguration;
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

import static org.mockito.Mockito.mock;

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

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }


    @Test
    public void welcome() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER")))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/pages/welcome.jsp"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void main() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/main").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/pages/main.jsp"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void mainForbidden() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/main").with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER")))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/pages/main.jsp"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void logout() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/logout").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

}