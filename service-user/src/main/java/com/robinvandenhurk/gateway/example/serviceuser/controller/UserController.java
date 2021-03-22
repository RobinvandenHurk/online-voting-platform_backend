package com.robinvandenhurk.gateway.example.serviceuser.controller;

import com.robinvandenhurk.gateway.example.serviceuser.domain.entity.Authority;
import com.robinvandenhurk.gateway.example.serviceuser.domain.entity.User;
import com.robinvandenhurk.gateway.example.serviceuser.domain.http.response.GetCurrentUserData;
import com.robinvandenhurk.gateway.example.serviceuser.domain.http.response.HttpResponse;
import com.robinvandenhurk.gateway.example.serviceuser.repository.AuthorityRepository;
import com.robinvandenhurk.gateway.example.serviceuser.repository.UserRepository;
import com.robinvandenhurk.gateway.library.userinjection.ForwardedHttpServletRequest;
import com.robinvandenhurk.gateway.library.userinjection.annotation.AuthorityRequired;
import com.robinvandenhurk.gateway.library.userinjection.annotation.AuthorityRequiredAspect;
import com.robinvandenhurk.gateway.library.userinjection.principal.GatewayUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: UserController
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    @Autowired
    public UserController(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;

        if (userRepository.findByEmail("robin@robinhood.com") == null) {
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
    }

    @GetMapping
    @AuthorityRequired(authority = "VIEW_USER_DATA")
    public ResponseEntity<HttpResponse> getCurrentUserData(ForwardedHttpServletRequest request) {
        GatewayUserPrincipal gatewayUser = request.getUserPrincipal();

        Optional<User> optionalUser = userRepository.findById(gatewayUser.getId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(new HttpResponse(new GetCurrentUserData(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail())));
        } else {
//            We should never get here
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new HttpResponse(""));
        }
    }
}
