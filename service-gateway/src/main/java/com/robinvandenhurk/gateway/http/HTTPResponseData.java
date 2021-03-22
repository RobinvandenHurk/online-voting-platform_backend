package com.robinvandenhurk.gateway.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: HTTPResponse
 */

public class HTTPResponseData {

    private int code;
    private String body;
    private boolean success;
    private Exception exception;

    public HTTPResponseData(int code, String body) {
        this.code = code;
        this.body = body;
        this.success = true;
    }

    public HTTPResponseData(int code) {
        this.success = false;
        this.code = code;
    }

    public HTTPResponseData(Exception exception) {
        this.success = false;
        this.exception = exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    public <T> T getParsedData(Class<T> type) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> root = objectMapper.readValue(body, Map.class);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(objectMapper.writeValueAsString(root.get("data")), type);
    }

    public Exception getException() {
        return exception;
    }
}
