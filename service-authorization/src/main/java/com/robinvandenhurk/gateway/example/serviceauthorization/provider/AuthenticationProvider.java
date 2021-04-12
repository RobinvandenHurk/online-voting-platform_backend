package com.robinvandenhurk.gateway.example.serviceauthorization.provider;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Credentials;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.CredentialsRepository;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: AuthenticationProvider
 */

public class AuthenticationProvider {

    public boolean authenticate(CredentialsRepository repository, String email, String password) {
        boolean authenticated = false;
        Credentials credentials = repository.findByEmail(email);

        if (credentials != null) {
//            User exists
            if (credentials.getPasswordHash().equals(new HashProvider().pbkdf2(password))) {
//                Passwords match
                if (credentials.isEnabled()) {
//                    User is enabled
                    authenticated = true;
                }
            }
        }

        return authenticated;
    }

}
