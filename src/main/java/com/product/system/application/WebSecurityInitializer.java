package com.product.system.application;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

/**
 * Created by Sonikb on 16.08.2017.
 */
public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer{
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {   // Another variant to encode to UTF-8
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
