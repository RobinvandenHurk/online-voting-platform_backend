package com.robinvandenhurk.gateway.security.filter;

import com.robinvandenhurk.gateway.http.ExternallyAuthenticatedAuthenticationToken;
import com.robinvandenhurk.gateway.http.HTTPClient;
import com.robinvandenhurk.gateway.http.HTTPRequestData;
import com.robinvandenhurk.gateway.http.HTTPResponseData;
import com.robinvandenhurk.gateway.http.request.VerifyRequest;
import com.robinvandenhurk.gateway.http.response.VerifyResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: RemoteAuthorizationHeaderFilter
 */
public class RemoteAuthorizationHeaderFilter extends OncePerRequestFilter {

    private final int SERVER_PORT;

    public RemoteAuthorizationHeaderFilter(int server_port) {
        SERVER_PORT = server_port;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//      Send request to remote authorization server
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.length() > 0) {
//            Request to localhost to be routed by the gateway
            HTTPRequestData requestData = new HTTPRequestData(new URL("http://localhost:" + SERVER_PORT + "/auth/token/verify"), "POST");
            VerifyRequest verifyRequest = new VerifyRequest(authorizationHeader);

            requestData.setBody(verifyRequest);

            HTTPResponseData response = new HTTPClient().send(requestData);
            VerifyResponse verifyResponse = response.getParsedData(VerifyResponse.class);

//          If response is OK, set authenticated to true
            if (verifyResponse.isValid()) {
                List<GrantedAuthority> authorities = verifyResponse.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                SecurityContextHolder.getContext().setAuthentication(new ExternallyAuthenticatedAuthenticationToken(verifyResponse.getId(), verifyResponse.getEmail(), authorities));
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
