package com.robinvandenhurk.gateway.example.serviceuser.controller;

import com.robinvandenhurk.gateway.example.serviceuser.domain.entity.User;
import com.robinvandenhurk.gateway.example.serviceuser.domain.messaging.UserCreationMessage;
import com.robinvandenhurk.gateway.example.serviceuser.domain.messaging.UserDeletionMessage;
import com.robinvandenhurk.gateway.example.serviceuser.domain.http.request.CreateUserRequest;
import com.robinvandenhurk.gateway.example.serviceuser.domain.http.request.UpdateUserRequest;
import com.robinvandenhurk.gateway.example.serviceuser.domain.http.response.GetCurrentUserData;
import com.robinvandenhurk.gateway.example.serviceuser.domain.messaging.UserUpdatingMessage;
import com.robinvandenhurk.gateway.example.serviceuser.messaging.MessageSender;
import com.robinvandenhurk.gateway.example.serviceuser.repository.UserRepository;
import com.robinvandenhurk.gateway.library.userinjection.ForwardedHttpServletRequest;
import com.robinvandenhurk.gateway.library.userinjection.annotation.AuthenticationRequired;
import com.robinvandenhurk.gateway.library.userinjection.annotation.AuthorityRequired;
import com.robinvandenhurk.gateway.library.userinjection.domain.http.response.HttpResponse;
import com.robinvandenhurk.gateway.library.userinjection.principal.AuthenticatedGatewayUserPrincipal;
import com.robinvandenhurk.gateway.library.userinjection.principal.GatewayUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private MessageSender messageSender;

    @Autowired
    public UserController(UserRepository userRepository, MessageSender messageSender) {
        this.userRepository = userRepository;
        this.messageSender = messageSender;
    }

    @GetMapping
    @AuthenticationRequired
    public HttpResponse<?> getCurrentUserData(ForwardedHttpServletRequest request) {
        GatewayUserPrincipal gatewayUser = request.getUserPrincipal();

        Optional<User> optionalUser = userRepository.findById(gatewayUser.getId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new HttpResponse(new GetCurrentUserData(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));
        } else {
//            We should never get here
            return HttpResponse.createForbidden();
        }
    }

    @PutMapping
    @AuthenticationRequired
    public HttpResponse<?> updateUser(ForwardedHttpServletRequest request, @RequestBody @Valid UpdateUserRequest data) {
        AuthenticatedGatewayUserPrincipal gatewayUser = (AuthenticatedGatewayUserPrincipal) request.getUserPrincipal();

        User user = userRepository.findById(gatewayUser.getId()).get();

        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail());

        user = userRepository.save(user);

        messageSender.send(new UserUpdatingMessage(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));

        return HttpResponse.createOK();
    }


    @PostMapping
    public HttpResponse<?> createUser(@RequestBody @Valid CreateUserRequest request) {
//        Verify if email exists
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
//            Create the user

            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());

            user = userRepository.save(user);

            messageSender.send(new UserCreationMessage(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), request.getPassword()));

            return HttpResponse.createOK();
        } else {
            return HttpResponse.createConflict("User with email already exists");
        }
    }

    @DeleteMapping("/{id}")
    @AuthorityRequired(authority = "USER_OTHER_DELETE")
    public HttpResponse<?> deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            messageSender.send(new UserDeletionMessage(user.getId()));

            userRepository.deleteById(user.getId());

            return HttpResponse.createOK();
        } else {
            return HttpResponse.createNotFound();
        }
    }
}
