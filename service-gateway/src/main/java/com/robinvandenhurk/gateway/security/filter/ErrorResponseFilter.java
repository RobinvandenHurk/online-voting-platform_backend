package com.robinvandenhurk.gateway.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletResponse;

/**
 * Author:    Robin van den Hurk
 * Date:      17/03/2021
 * File name: ErrorResponseFilter
 */

/*
 This filter catches all 5xx status responses and logs them
 */
public class ErrorResponseFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        int status = response.getStatus();

        return (status >= 500 && status < 600);
    }

    @Override
    public Object run() throws ZuulException {
        // TODO: Better logging
        String url = RequestContext.getCurrentContext().getRequest().getRequestURL().toString();
        System.out.println("[ERROR] Request returned 5xx status. " + url);

        return null;
    }
}
