package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: HttpResponse
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse {
    private HttpResponseData data;
    private String error;
    private boolean success;

    public HttpResponse(String error) {
        this.success = false;
        this.error = error;
    }

    public HttpResponse() {
        this.success = true;
    }

    public HttpResponse(HttpResponseData data) {
        this.data = data;
        this.success = true;
    }

    public String getError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }

    public HttpResponseData getData() {
        return data;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
