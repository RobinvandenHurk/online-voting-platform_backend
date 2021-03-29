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

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
