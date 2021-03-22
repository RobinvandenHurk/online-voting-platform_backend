package com.robinvandenhurk.gateway.http.response;

/**
 * Author:    Robin van den Hurk
 * Date:      12/03/2021
 * File name: AuthResponse
 */

public class AuthResponse {

    private String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
