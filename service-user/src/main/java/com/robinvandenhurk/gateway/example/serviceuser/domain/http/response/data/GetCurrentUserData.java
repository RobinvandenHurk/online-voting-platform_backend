package com.robinvandenhurk.gateway.example.serviceuser.domain.http.response.data;

import com.robinvandenhurk.gateway.example.serviceuser.domain.http.response.HttpResponseData;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: GetCurrentUserData
 */

public class GetCurrentUserData extends HttpResponseData {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public GetCurrentUserData(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
