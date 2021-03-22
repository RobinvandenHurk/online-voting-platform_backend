package com.robinvandenhurk.onlinevotingplatform.servicecandidate.repositories;

import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: PartyRepository
 */

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
