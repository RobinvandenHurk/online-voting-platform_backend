package com.robinvandenhurk.gateway.example.serviceuser;

import com.robinvandenhurk.gateway.example.serviceuser.domain.entity.Authority;
import com.robinvandenhurk.gateway.example.serviceuser.domain.entity.User;
import com.robinvandenhurk.gateway.example.serviceuser.repository.AuthorityRepository;
import com.robinvandenhurk.gateway.example.serviceuser.repository.UserRepository;
import com.robinvandenhurk.gateway.library.userinjection.UserInjectionLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Import(UserInjectionLoader.class)
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }

	private Environment environment;

    public ServiceUserApplication(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Profile("dev")
    public CommandLineRunner demo(UserRepository userRepository, AuthorityRepository authorityRepository) {
        if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
            // P@ssw0rd
            String hash = "2355aa883ba9bab232c85dccc9f3dce6fe97b6c0a1a1531971fc105516653309ff6d2246dec6aad1ba08a77775c8c052b5715fa1f68a207143ef9dc664d10c6b";
            List<Authority> authorities = new ArrayList<>();

            authorities.add(authorityRepository.save(new Authority("UPDATE")));
            authorities.add(authorityRepository.save(new Authority("READ")));
            authorities.add(authorityRepository.save(new Authority("DELETE")));
            authorities.add(authorityRepository.save(new Authority("CREATE")));
            authorities.add(authorityRepository.save(new Authority("VIEW_USER_DATA")));

            User user = new User("Robin", "Hood", "robin@robinhood.com", hash, true, authorities);

            userRepository.save(user);

        }
        return null;
    }

}
