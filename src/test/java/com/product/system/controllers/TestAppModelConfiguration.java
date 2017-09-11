package com.product.system.controllers;

import com.product.system.services.ProductService;
import com.product.system.services.UserDetailedServiceImpl;
import com.product.system.services.UserRoleService;
import com.product.system.services.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Sonik on 10.09.2017.
 */
@Configuration
public class TestAppModelConfiguration {

    @Bean
    public ProductService productService(){
        return Mockito.mock(ProductService.class);
    }

    @Bean
    public UserDetailedServiceImpl userDetailedServiceImpl(){
        return Mockito.mock(UserDetailedServiceImpl.class);
    }

    @Bean
    public UserRoleService userRoleService(){
        return Mockito.mock(UserRoleService.class);
    }

    @Bean
    public UserService userService(){
        return Mockito.mock(UserService.class);
    }
}
