package com.robinvandenhurk.gateway.example.serviceauthorization.messaging.receivers;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Authority;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Credentials;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.messaging.UserCreationMessage;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.messaging.UserUpdatingMessage;
import com.robinvandenhurk.gateway.example.serviceauthorization.provider.HashProvider;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.AuthorityRepository;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.CredentialsRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: UserUpdatingMessageReceiver
 */

@Component
public class UserUpdatingMessageReceiver {

    private CredentialsRepository credentialsRepository;

    @Autowired
    public UserUpdatingMessageReceiver(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @RabbitListener(queues = "ovp.user.updating")
    public void receiveMessage(UserUpdatingMessage message) {
        Optional<Credentials> optionalCredentials = credentialsRepository.findById(message.getId());

        if (optionalCredentials.isPresent()) {
            Credentials credentials = optionalCredentials.get();

            credentials.setEmail(message.getEmail());

            credentialsRepository.save(credentials);
        }
    }
}
