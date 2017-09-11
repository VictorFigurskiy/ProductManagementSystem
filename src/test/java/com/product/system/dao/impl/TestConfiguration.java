package com.product.system.dao.impl;

import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Sonikb on 06.09.2017.
 */
@Configuration
public class TestConfiguration {

    @Bean
    public SessionFactory sessionFactory(){
        return Mockito.mock(SessionFactory.class);
    }
}
