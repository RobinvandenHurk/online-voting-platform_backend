package com.robinvandenhurk.gateway.example.serviceuser.repository;

import com.robinvandenhurk.gateway.example.serviceuser.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: UserRepository
 */

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
