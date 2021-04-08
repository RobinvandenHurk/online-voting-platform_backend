package com.robinvandenhurk.gateway.example.serviceuser.domain.entity.messaging;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: UserCreationMessage
 */

public class UserCreationMessage implements Message {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserCreationMessage(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    @Override
    public String getRoutingKey() {
        return "ovp.user.creation";
    }

}
