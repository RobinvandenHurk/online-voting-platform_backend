package com.robinvandenhurk.gateway.http.request;

/**
 * Author:    Robin van den Hurk
 * Date:      12/03/2021
 * File name: AuthRequest
 */

public class AuthRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}