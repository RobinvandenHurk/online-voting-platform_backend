package com.robinvandenhurk.gateway.example.serviceuser.domain.messaging;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: UserUpdatingMessage
 */

public class UserUpdatingMessage implements Message {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public UserUpdatingMessage(Long id, String firstName, String lastName, String email) {
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

    @Override
    public String getRoutingKey() {
        return "ovp.user.updating";
    }

}
