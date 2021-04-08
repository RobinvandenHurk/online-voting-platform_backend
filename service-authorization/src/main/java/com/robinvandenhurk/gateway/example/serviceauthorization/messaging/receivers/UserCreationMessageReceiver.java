package com.robinvandenhurk.gateway.example.serviceauthorization.messaging.receivers;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Authority;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Credentials;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.messaging.UserCreationMessage;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.messaging.UserDeletionMessage;
import com.robinvandenhurk.gateway.example.serviceauthorization.provider.HashProvider;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.AuthorityRepository;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.CredentialsRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: UserCreationMessageReceiver
 */

@Component
public class UserCreationMessageReceiver {

    private CredentialsRepository credentialsRepository;
    private AuthorityRepository authorityRepository;
    private HashProvider hashProvider;

    @Autowired
    public UserCreationMessageReceiver(CredentialsRepository credentialsRepository, AuthorityRepository authorityRepository) {
        this.credentialsRepository = credentialsRepository;
        this.authorityRepository = authorityRepository;
        this.hashProvider = new HashProvider();
    }

    @RabbitListener(queues = "ovp.user.creation")
    public void receiveMessage(UserCreationMessage message) {
        Credentials credentials = new Credentials();

        credentials.setUserId(message.getId());
        credentials.setEmail(message.getEmail());
        credentials.setPasswordHash(hashProvider.pbkdf2(message.getPassword()));
        credentials.setEnabled(true);
        credentials.setAuthorities(getDefaultAuthorities());

        credentialsRepository.save(credentials);
    }

    private List<Authority> getDefaultAuthorities() {
        List<Authority> authorities = new ArrayList<>();
        String[] authorityNames = {
            "USER_SELF_VIEW_DATA",
            "USER_OTHER_DELETE"
        };

        for (String authorityName : authorityNames) {
            Optional<Authority> optionalAuthority = authorityRepository.findAuthorityByName(authorityName);

            if (optionalAuthority.isPresent()) {
                authorities.add(optionalAuthority.get());
            } else {
//                Authority doesn't exist. Create it, and add it to the list
                Authority authority = new Authority();
                authority.setName(authorityName);

                authority = authorityRepository.save(authority);

                authorities.add(authority);
            }
        }

        return authorities;
    }

}
