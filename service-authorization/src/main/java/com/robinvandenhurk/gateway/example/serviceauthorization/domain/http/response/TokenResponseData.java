package com.robinvandenhurk.gateway.example.serviceauthorization.domain.http.response;

/**
 * Author:    Robin van den Hurk
 * Date:      12/03/2021
 * File name: TokenResponseData
 */

public class TokenResponseData extends HttpResponseData {

    private String token;

    public TokenResponseData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
