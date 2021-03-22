package com.robinvandenhurk.gateway.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.robinvandenhurk.gateway.http.ExternallyAuthenticatedAuthenticationToken;
import com.robinvandenhurk.gateway.http.headers.UserDetailsHeader;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: AddUserDetailHeadersFilter
 */

/*
 This class is responsible for adding additional headers containing user details to the request.
 This allows services behind the gateway to retrieve user details
 */
public class AddUserDetailHeadersFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetailsHeader header;

//        Remove existing header if send from client
        requestContext.addZuulRequestHeader("gateway-user", null);

        if (securityContext.getAuthentication() instanceof ExternallyAuthenticatedAuthenticationToken) {
            ExternallyAuthenticatedAuthenticationToken authentication = (ExternallyAuthenticatedAuthenticationToken) securityContext.getAuthentication();
            List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            header = new UserDetailsHeader(authentication.getId(), (String) authentication.getPrincipal(), authorities);
        } else {
            header = new UserDetailsHeader();
        }

        requestContext.addZuulRequestHeader("gateway-user", Base64.getEncoder().encodeToString(header.toString().getBytes()));

        return null;
    }
}
