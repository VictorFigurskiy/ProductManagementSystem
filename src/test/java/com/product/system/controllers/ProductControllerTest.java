package com.product.system.controllers;

import com.product.system.configuration.MvcConfiguration;
import com.product.system.configuration.SecurityConfiguration;
import com.product.system.controllers.config.TestAppModelConfiguration;
import com.product.system.entity.ProductEntity;
import com.product.system.services.ProductService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
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

/**
 * Created by Sonik on 09.09.2017.
 */
@RunWith(value = SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        TestAppModelConfiguration.class,
        MvcConfiguration.class,
        SecurityConfiguration.class})
public class ProductControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProductService productService;

    private ProductEntity productEntity;

    @Before
    public void setUp() throws Exception {
        productEntity = mock(ProductEntity.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void productsList() throws Exception {
        when(productService.getAll()).thenReturn(Collections.singletonList(productEntity));

        mvc.perform(MockMvcRequestBuilders.get("/productEntity/list").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productList", CoreMatchers.equalTo(Collections.singletonList(productEntity))))
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }

    @Test
    public void validateProduct() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/productEntity/validate").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productFromPage", new ProductEntity()))
                .andExpect(MockMvcResultMatchers.view().name("add_product"));
    }

    @Test
    public void save() throws Exception {
        when(productEntity.getName()).thenReturn("Test ProductEntity");

        doAnswer(invocation -> {
            ProductEntity productEntity1 = invocation.getArgument(0);
            assertEquals("Test ProductEntity", productEntity1.getName());
            return null;
        }).when(productService).save(productEntity);

        productService.save(productEntity);

        verify(productService, atLeastOnce()).save(productEntity);

        when(productService.getAll()).thenReturn(Collections.singletonList(productEntity));

        mvc.perform(MockMvcRequestBuilders.post("/productEntity/save").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productList", CoreMatchers.equalTo(Collections.singletonList(productEntity))))
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }

    @Test
    public void delete() throws Exception {
        when(productService.getById(1)).thenReturn(productEntity);
        assertEquals(productEntity, productService.getById(1));
        verify(productService, times(1)).getById(1);

        doAnswer(invocation -> null).when(productService).remove(productEntity);

        productService.remove(productEntity);

        verify(productService, atMost(1)).remove(productEntity);

        ProductEntity otherProductEntity = mock(ProductEntity.class);
        when(productService.getAll()).thenReturn(Collections.singletonList(otherProductEntity));

        mvc.perform(MockMvcRequestBuilders.get("/productEntity/delete1").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productList", CoreMatchers.equalTo(Collections.singletonList(otherProductEntity))))
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }

    @Test
    public void validateUser() throws Exception {
        when(productService.getById(1)).thenReturn(productEntity);

        mvc.perform(MockMvcRequestBuilders.get("/productEntity/update_product1").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("updateProduct", new ProductEntity()))
                .andExpect(MockMvcResultMatchers.model().attribute("productEntity", productEntity))
                .andExpect(MockMvcResultMatchers.view().name("update"));
    }

    @Test
    public void validateUserWithUserRole() throws Exception {
        when(productService.getById(1)).thenReturn(productEntity);

        mvc.perform(MockMvcRequestBuilders.get("/productEntity/update_product1").with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER")))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Ignore
    @Test
    public void update() throws Exception {
        when(productEntity.getName()).thenReturn("Test productEntity");

        doAnswer(invocation -> {
            ProductEntity productEntity1 = invocation.getArgument(0);
            assertEquals("Test productEntity", productEntity1.getName());
            return null;
        }).when(productService).update(productEntity);

        productService.update(productEntity);

        verify(productService, atLeastOnce()).update(productEntity);

        when(productService.getAll()).thenReturn(Collections.singletonList(productEntity));

//        verify(productService, times(1)).getAll();

        mvc.perform(MockMvcRequestBuilders.post("/productEntity/update").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productList", CoreMatchers.equalTo(Collections.singletonList(productEntity))))
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }
}