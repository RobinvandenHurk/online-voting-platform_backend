package com.robinvandenhurk.gateway.security;

import com.robinvandenhurk.gateway.security.filter.RemoteAuthorizationHeaderFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Author:    Robin van den Hurk
 * Date:      12/03/2021
 * File name: RemoteSecurityConfigurer
 */

public class RemoteSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final int SERVER_PORT;

    public RemoteSecurityConfigurer(int server_port) {
        SERVER_PORT = server_port;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new RemoteAuthorizationHeaderFilter(SERVER_PORT), UsernamePasswordAuthenticationFilter.class);
    }

}