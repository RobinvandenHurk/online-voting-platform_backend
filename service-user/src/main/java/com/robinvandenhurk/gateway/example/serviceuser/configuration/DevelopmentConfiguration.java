package com.robinvandenhurk.gateway.example.serviceuser.configuration;

import com.robinvandenhurk.gateway.example.serviceuser.domain.entity.User;
import com.robinvandenhurk.gateway.example.serviceuser.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: DevelopmentConfiguration
 */

@Configuration
public class DevelopmentConfiguration {

    @Bean
    @Profile("dev")
    public CommandLineRunner demo(UserRepository userRepository) {
        if (!userRepository.existsById(1L)) {
            User user = new User();
            user.setFirstName("Robin");
            user.setLastName("Hood");
            user.setEmail("robin@robinvandenhurk.com");

            userRepository.save(user);
        }

        return null;
    }

}
