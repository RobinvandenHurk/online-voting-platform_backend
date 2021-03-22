package com.robinvandenhurk.gateway.example.serviceauthorization.repository;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: UserRepository
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
