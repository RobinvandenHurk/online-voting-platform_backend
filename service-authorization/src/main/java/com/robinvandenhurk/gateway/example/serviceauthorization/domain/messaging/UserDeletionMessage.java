package com.robinvandenhurk.gateway.example.serviceauthorization.domain.messaging;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: UserDeletionMessage
 */

public class UserDeletionMessage {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
