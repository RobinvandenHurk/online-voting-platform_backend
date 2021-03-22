package com.robinvandenhurk.gateway.example.serviceauthorization.repository;

import com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: AuthorityRepository
 */

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
