package com.product.system.application;

import com.product.system.configuration.ModelConfiguration;
import com.product.system.configuration.MvcConfiguration;
import com.product.system.configuration.SecurityConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Sonikb on 12.08.2017.
 */

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // All other configuration classes goes here
        // Here we pass spring security configuration
        // But we must also extend AbstractSecurityWebApplicationInitializer same as with dispatcher servlet.
        return new Class[] {ModelConfiguration.class, SecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Web MVC configuration goes here
        return new Class[]{MvcConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {  // More variants to use filter for UTF-8
//        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
//        encodingFilter.setInitParameter("encoding", "UTF-8");
//        encodingFilter.setInitParameter("forceEncoding", "true");
//        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
//    }

//    @Override
//    protected Filter[] getServletFilters() { // And another variant for UTF-8
//        haracterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        return new Filter[] {characterEncodingFilter};
//    }
}

// What magic stands before this?
//
// 1. First with help of SPI class SpringServletContainerInitializer is loaded and onStartup method called.
// 2. With help of SPI and @HandlesTypes(WebApplicationInitializer.class) it will try to find all classes Spring MVC must load in start
//    We have two : WebApplicationInitializer and SecurityWebApplicationInitializer
// 3. Create Spring Context from servlet config classes:
//
//  protected WebApplicationContext createServletApplicationContext() {
//    AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
//    Class<?>[] configClasses = getServletConfigClasses();
//    if (!ObjectUtils.isEmpty(configClasses)) {
//      servletAppContext.register(configClasses);
//    }
//    return servletAppContext;
//  }
//
// 4. Register Dispatcher servlet and pass this newly created context to it.
//
//  protected void registerDispatcherServlet(ServletContext servletContext) {
//    String servletName = getServletName();
//    Assert.hasLength(servletName, "getServletName() must not return empty or null");
//
//    WebApplicationContext servletAppContext = createServletApplicationContext();
//    Assert.notNull(servletAppContext,
//        "createServletApplicationContext() did not return an application " +
//        "context for servlet [" + servletName + "]");
//
//    FrameworkServlet dispatcherServlet = createDispatcherServlet(servletAppContext);
//    dispatcherServlet.setContextInitializers(getServletApplicationContextInitializers());
//
//    ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, dispatcherServlet);
//    Assert.notNull(registration,
//        "Failed to register servlet with name '" + servletName + "'." +
//        "Check if there is another servlet registered under the same name.");
//
//    registration.setLoadOnStartup(1);
//    registration.addMapping(getServletMappings());
//    registration.setAsyncSupported(isAsyncSupported());
//
//    Filter[] filters = getServletFilters();
//    if (!ObjectUtils.isEmpty(filters)) {
//      for (Filter filter : filters) {
//        registerServletFilter(servletContext, filter);
//      }
//    }
//
//    customizeRegistration(registration);
//  }
//
//  5. Then called AbstractSecurityWebApplicationInitializer, get loaded root context of the application.
//  6. And register Spring security filter.
//
//  private void insertSpringSecurityFilterChain(ServletContext servletContext) {
//    String filterName = DEFAULT_FILTER_NAME;
//    DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy(
//        filterName);
//    String contextAttribute = getWebApplicationContextAttribute();
//    if (contextAttribute != null) {
//      springSecurityFilterChain.setContextAttribute(contextAttribute);
//    }
//    registerFilter(servletContext, true, filterName, springSecurityFilterChain);
//  }