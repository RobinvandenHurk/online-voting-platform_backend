package com.robinvandenhurk.gateway.http.headers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: UserDetailsHeader
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsHeader {

    private String id;
    private String email;
    private List<String> authorities;

    public UserDetailsHeader(String id, String email, List<String> authorities) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
    }

    public UserDetailsHeader() {
//        Empty constructor for anonymous user
    }

    public String getEmail() {
        return email;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public String getId() {
        return id;
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
