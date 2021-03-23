package com.robinvandenhurk.gateway.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;

/**
 * Author:    Robin van den Hurk
 * Date:      17/03/2021
 * File name: ErrorResponseFilter
 */

/*
 This filter logs all requests in the terminal
 */
public class LoggingFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "route";
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
        // TODO: Better logging
        String url = RequestContext.getCurrentContext().getRequest().getRequestURL().toString();
        URL routeHost = RequestContext.getCurrentContext().getRouteHost();

        System.out.println("Forwarding request: " + url + " -> " + routeHost.toString());

        return null;
    }
}
