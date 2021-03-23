package com.robinvandenhurk.onlinevotingplatform.servicevotingform.repositories;

import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: PartyRepository
 */

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
    Optional<Election> findAllByName(String name);
}
