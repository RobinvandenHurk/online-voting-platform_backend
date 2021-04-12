package com.robinvandenhurk.gateway.example.serviceauthorization.repository;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: UserRepository
 */

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Credentials findByEmail(String email);
}
