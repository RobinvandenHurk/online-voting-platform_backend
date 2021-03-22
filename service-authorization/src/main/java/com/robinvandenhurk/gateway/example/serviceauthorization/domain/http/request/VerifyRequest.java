package com.robinvandenhurk.gateway.example.serviceauthorization.domain.http.request;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: VerifyRequest
 */

public class VerifyRequest {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
