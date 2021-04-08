package com.robinvandenhurk.gateway.example.serviceauthorization.domain.messaging;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: UserUpdatingMessage
 */

public class UserUpdatingMessage {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

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
