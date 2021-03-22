package com.robinvandenhurk.gateway.http.response;

import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: VerifyResponse
 */

public class VerifyResponse {

    private boolean valid;
    private String id;
    private String email;
    private List<String> authorities;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
