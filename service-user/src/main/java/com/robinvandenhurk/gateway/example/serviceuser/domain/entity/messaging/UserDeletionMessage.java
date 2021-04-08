package com.robinvandenhurk.gateway.example.serviceuser.domain.entity.messaging;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: UserDeletionMessage
 */

public class UserDeletionMessage implements Message {

    private Long id;

    public UserDeletionMessage(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getRoutingKey() {
        return "ovp.user.deletion";
    }

}
