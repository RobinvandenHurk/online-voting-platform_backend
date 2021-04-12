package com.robinvandenhurk.gateway.example.serviceauthorization.messaging.receivers;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.messaging.UserDeletionMessage;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.CredentialsRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: UserDeletionMessageReceiver
 */

@Component
public class UserDeletionMessageReceiver {

    private CredentialsRepository credentialsRepository;

    @Autowired
    public UserDeletionMessageReceiver(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @RabbitListener(queues = "ovp.user.deletion")
    public void receiveMessage(UserDeletionMessage message) {
        credentialsRepository.deleteById(message.getId());
    }

}
