package com.robinvandenhurk.gateway.example.serviceuser.domain.http.response;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: GetCurrentUserData
 */

public class GetCurrentUserData extends HttpResponseData {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public GetCurrentUserData(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
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
