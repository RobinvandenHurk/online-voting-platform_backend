package com.robinvandenhurk.gateway.example.serviceauthorization.config;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Authority;
import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Credentials;
import com.robinvandenhurk.gateway.example.serviceauthorization.provider.HashProvider;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.AuthorityRepository;
import com.robinvandenhurk.gateway.example.serviceauthorization.repository.CredentialsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      08/04/2021
 * File name: DevelopmentConfiguration
 */

@Configuration
public class DevelopmentConfiguration {

    @Bean
    @Profile("dev")
    public CommandLineRunner demo(CredentialsRepository credentialsRepository, AuthorityRepository authorityRepository) {
        if (!credentialsRepository.existsById(1L)) {
            // P@ssw0rd
            List<Authority> authorities = new ArrayList<>();

            authorities.add(authorityRepository.save(new Authority("UPDATE")));
            authorities.add(authorityRepository.save(new Authority("READ")));
            authorities.add(authorityRepository.save(new Authority("DELETE")));
            authorities.add(authorityRepository.save(new Authority("CREATE")));
            authorities.add(authorityRepository.save(new Authority("VIEW_USER_DATA")));

            Credentials credentials = new Credentials();
            credentials.setUserId(1L);
            credentials.setEmail("robin@robinvandenhurk.com");
            credentials.setPasswordHash(new HashProvider().pbkdf2("P@ssw0rd"));
            credentials.setEnabled(true);
            credentials.setAuthorities(authorities);

            credentialsRepository.save(credentials);
        }

        return null;
    }

}
