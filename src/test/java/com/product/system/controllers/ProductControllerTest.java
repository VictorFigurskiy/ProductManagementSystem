package com.product.system.controllers;

import com.product.system.configuration.MvcConfiguration;
import com.product.system.configuration.SecurityConfiguration;
import com.product.system.entity.Product;
import com.product.system.services.ProductService;
import org.hamcrest.CoreMatchers;
import org.junit.After;
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
import java.util.List;

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

    private Product product;

    @Before
    public void setUp() throws Exception {
        product = mock(Product.class);
        when(product.getManufacturer()).thenReturn("Apple");
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void productsList() throws Exception {
        when(productService.getAll()).thenReturn(Collections.singletonList(product));

        mvc.perform(MockMvcRequestBuilders.get("/product/list").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productList", CoreMatchers.equalTo(Collections.singletonList(product))))
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }

    @Test
    public void validateProduct() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/product/validate").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productFromPage", new Product()))
                .andExpect(MockMvcResultMatchers.view().name("add_product"));
    }

    @Test
    public void save() throws Exception {
        when(product.getName()).thenReturn("Test Product");

        doAnswer(invocation -> {
            Product product1 = invocation.getArgument(0);
            assertEquals("Test Product", product1.getName());
            return null;
        }).when(productService).save(product);

        productService.save(product);

        verify(productService, atLeastOnce()).save(product);

        when(productService.getAll()).thenReturn(Collections.singletonList(product));

        mvc.perform(MockMvcRequestBuilders.post("/product/save").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productList", CoreMatchers.equalTo(Collections.singletonList(product))))
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }

    @Test
    public void delete() throws Exception {
        when(productService.getById(1)).thenReturn(product);
        assertEquals(product, productService.getById(1));
        verify(productService, times(1)).getById(1);

        doAnswer(invocation -> null).when(productService).remove(product);

        productService.remove(product);

        verify(productService, atMost(1)).remove(product);

        Product otherProduct = mock(Product.class);
        when(productService.getAll()).thenReturn(Collections.singletonList(otherProduct));

        mvc.perform(MockMvcRequestBuilders.get("/product/delete1").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productList", CoreMatchers.equalTo(Collections.singletonList(otherProduct))))
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }

    @Test
    public void validateUser() throws Exception {
        when(productService.getById(1)).thenReturn(product);

        mvc.perform(MockMvcRequestBuilders.get("/product/update_product1").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("updateProduct", new Product()))
                .andExpect(MockMvcResultMatchers.model().attribute("product", product))
                .andExpect(MockMvcResultMatchers.view().name("update"));
    }

    @Test
    public void validateUserWithUserRole() throws Exception {
        when(productService.getById(1)).thenReturn(product);

        mvc.perform(MockMvcRequestBuilders.get("/product/update_product1").with(SecurityMockMvcRequestPostProcessors.user("test").roles("USER")))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void update() throws Exception {
        when(product.getName()).thenReturn("Test product");
        when(productService.getAll()).thenReturn(Collections.singletonList(null));

        doAnswer(invocation -> {
            Product product1 = invocation.getArgument(0);
            assertEquals("Test product", product1.getName());
            return null;
        }).when(productService).update(product);

//        verify(productService, atLeastOnce()).update(product);

        List<Product> productList = productService.getAll();

        verify(productService, times(1)).getAll();

        mvc.perform(MockMvcRequestBuilders.post("/product/update").with(SecurityMockMvcRequestPostProcessors.user("test").roles("ADMIN")))
//                .andExpect(MockMvcResultMatchers.)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("productList", productList))
                .andExpect(MockMvcResultMatchers.view().name("products"));
    }
}