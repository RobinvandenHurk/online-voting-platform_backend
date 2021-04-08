package com.robinvandenhurk.gateway.example.serviceuser.domain.http.response.data;

import com.robinvandenhurk.gateway.example.serviceuser.domain.http.response.HttpResponseData;

import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: BadRequestResponseData
 */

public class BadRequestResponseData extends HttpResponseData {

    private Map<String, String> errors;
    private String message;

    public BadRequestResponseData(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public BadRequestResponseData(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }
}
