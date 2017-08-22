package com.product.system.configuration;

import com.product.system.services.UserDetailedServiceImpl;
import com.product.system.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by Sonikb on 12.08.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailedServiceImpl userDetailedService(UserService userService){
        return new UserDetailedServiceImpl(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*CharacterEncodingFilter filter = new CharacterEncodingFilter(); // All this need to be added to encode in UTF-8
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);*/ //

        http.authorizeRequests()
                .antMatchers("/product/list").hasAnyRole("USER","ADMIN")
                .antMatchers("/product/**","/user/**").hasRole("ADMIN")
                .antMatchers("/register").permitAll()
                .antMatchers("/jpeg/**").authenticated()
                .antMatchers("/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }

}
