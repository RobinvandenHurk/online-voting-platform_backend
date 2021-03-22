package com.robinvandenhurk.gateway.http;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: ExternallyAuthenticatedAuthenticationToken
 */

public class ExternallyAuthenticatedAuthenticationToken extends AbstractAuthenticationToken {
    private final String id;
    private final Object principal;

    public ExternallyAuthenticatedAuthenticationToken(String id, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.id = id;
        this.principal = principal;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public String getId() {
        return id;
    }
}
