package com.robinvandenhurk.onlinevotingplatform.servicevotingform.repositories;

import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author:    Robin van den Hurk
 * Date:      25/03/2021
 * File name: PartyRepository
 */

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
}
