package com.robinvandenhurk.gateway.example.serviceuser.domain.http.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: UpdateUserRequest
 */

public class UpdateUserRequest {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    @NotEmpty
    private String email;

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
