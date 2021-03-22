package com.robinvandenhurk.gateway.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: HTTPRequest
 */

public class HTTPRequestData {

    private URL url;
    private Map<String, String> headers;
    private Object body;
    private boolean bodyAsJson;
    private String method;

    public HTTPRequestData(URL url, String method) {
        this.url = url;
        bodyAsJson = true;
        this.method = method;
        this.headers = new HashMap<>();
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    public String getBody() throws JsonProcessingException {
        if (bodyAsJson) {
            return new ObjectMapper().writeValueAsString(body);
        } else {
            return (String) body;
        }
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public boolean isBodyAsJson() {
        return bodyAsJson;
    }

    public void setBodyAsJson(boolean bodyAsJson) {
        this.bodyAsJson = bodyAsJson;
    }

    public int getBodyLength() {
        if (body == null) {
            return 0;
        } else if (isBodyAsJson()) {
            try {
                return getBody().length();
            } catch (JsonProcessingException e) {
                return 0;
            }
        } else {
            return body.toString().length();
        }
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> tempHeaders = new HashMap<>(this.headers);

        if (isBodyAsJson()) tempHeaders.put("Content-Type", "application/json");
        tempHeaders.put("Content-Length", Integer.toString(getBodyLength()));

        return tempHeaders;
    }

    public String getMethod() {
        return method;
    }
}
