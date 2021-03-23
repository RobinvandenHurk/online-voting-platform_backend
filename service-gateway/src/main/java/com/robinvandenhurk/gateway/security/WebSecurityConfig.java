package com.robinvandenhurk.gateway.security;

import com.robinvandenhurk.gateway.security.filter.AddUserDetailHeadersFilter;
import com.robinvandenhurk.gateway.security.filter.ErrorResponseFilter;
import com.robinvandenhurk.gateway.security.filter.LoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Author:    Robin van den Hurk
 * Date:      12/03/2021
 * File name: WebSecurityConfig
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${server.port}")
    private int server_port;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // Make gateway stateless. Without this setting authentication may persist even
        // When invalid tokens or credentials are supplied
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
//        http.authorizeRequests()
//                .antMatchers("/auth/**", "/public/**").permitAll()
//                // Disallow everything else..
//                .anyRequest().authenticated()
        ;

        // Apply authorization filter configuration
        http.apply(new RemoteSecurityConfigurer(server_port));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*/")//
                .antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
    }

    @Bean
    public AddUserDetailHeadersFilter userDetailHeadersFilter() {
        return new AddUserDetailHeadersFilter();
    }

    @Bean
    public ErrorResponseFilter errorResponseFilter() {
        return new ErrorResponseFilter();
    }

    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }

}
