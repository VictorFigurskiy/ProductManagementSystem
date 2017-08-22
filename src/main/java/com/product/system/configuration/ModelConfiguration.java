package com.product.system.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Sonikb on 08.08.2017.
 */
@Configuration
@ComponentScan({"com.product.system.dao", "com.product.system.services"})
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement  // Включает использование транзакций
public class ModelConfiguration {

    @Value("${db.url}")//spring expression language
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver}")
    private String driver;
    @Value("${db.dialect}")
    private String dialect;
    @Value("${hibernate.show_sql}")
    private String showSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    @Value("${hibernate.enable_lazy_load_no_trans}")
    private String enableLazyLoadOnTrans;
    @Value("${current_session_context_class}")
    private String current_session_context_class;
    @Value("${hibernate.connection.charSet}")
    private String charSet;
    @Value("${connection.characterEncoding}")
    private String characterEncoding;

    @Bean(destroyMethod = "close") // (destroyMethod = "close") данный бин вызовет метод close() при уничтожении класса(при завершении работы)
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        properties.put("hibernate.enable_lazy_load_no_trans", enableLazyLoadOnTrans);
        properties.put("current_session_context_class",current_session_context_class);
        properties.put("hibernate.connection.charSet", charSet);
        properties.put("connection.characterEncoding", characterEncoding);
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPackagesToScan("com.product.system.entity");
        bean.setHibernateProperties(hibernateProperties());
        return bean;
    }

    @Autowired // но если внутри то в данном случае @Autowired не обязателен
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        return new HibernateTransactionManager(sessionFactory);
    }

}
