package com.robinvandenhurk.gateway.example.serviceauthorization.provider;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.User;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.UserRepository;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: AuthenticationProvider
 */

public class AuthenticationProvider {

    public boolean authenticate(UserRepository repository, String email, String password) {
        boolean authenticated = false;
        User user = repository.findByEmail(email);

        if (user != null) {
//            User exists
            if (user.getPasswordHash().equals(new HashProvider().pbkdf2(password))) {
//                Passwords match
                if (user.isEnabled()) {
//                    User is enabled
                    authenticated = true;
                }
            }
        }

        return authenticated;
    }

}
