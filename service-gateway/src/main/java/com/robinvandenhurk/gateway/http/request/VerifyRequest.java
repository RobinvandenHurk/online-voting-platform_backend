package com.robinvandenhurk.gateway.http.request;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: VerifyRequest
 */

public class VerifyRequest {
    private String token;

    public VerifyRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
