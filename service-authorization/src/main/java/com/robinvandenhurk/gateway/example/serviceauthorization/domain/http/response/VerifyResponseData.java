package com.robinvandenhurk.gateway.example.serviceauthorization.domain.http.response;

import java.util.Date;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: VerifyResponseData
 */

public class VerifyResponseData extends HttpResponseData {

    private boolean valid;
    private String id;
    private String email;
    private List<String> authorities;

    public VerifyResponseData(String id, String email, List<String> authorities) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
        this.valid = true;
    }

    public VerifyResponseData() {
        this.valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
