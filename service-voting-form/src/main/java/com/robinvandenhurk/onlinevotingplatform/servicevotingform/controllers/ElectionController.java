package com.robinvandenhurk.onlinevotingplatform.servicevotingform.controllers;

import com.robinvandenhurk.gateway.library.userinjection.ForwardedHttpServletRequest;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Election;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Party;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.request.CreateElectionRequestData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.HttpResponse;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data.CreateElectionResponseData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data.GetAllElectionsResponseData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data.GetSingleElectionResponseData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.repositories.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.*;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: VoteFormController
 */

@RestController()
@RequestMapping("/voting-form")
public class ElectionController {

    private ElectionRepository electionRepository;

    @Autowired
    public ElectionController(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    @GetMapping
    public HttpResponse<?> getAllElections() {
        List<Election> elections = this.electionRepository.findAll();
        List<GetSingleElectionResponseData> filteredElections = new ArrayList<>();

        elections.forEach(election -> filteredElections.add(new GetSingleElectionResponseData(election.getId(), election.getName(), election.getParties(), election.getStartDateTime(), election.getEndDateTime(), election.getCreatedAt(), election.getCreatedBy())));

        return new HttpResponse<>(new GetAllElectionsResponseData(filteredElections));
    }

    @GetMapping("/{id}")
    public HttpResponse<?> getElection(@PathVariable long id) {
        Optional<Election> optionalElection = this.electionRepository.findById(id);

        if (optionalElection.isPresent()) {
            Election election = optionalElection.get();

            return new HttpResponse<>(new GetSingleElectionResponseData(election.getId(), election.getName(), election.getParties(), election.getStartDateTime(), election.getEndDateTime(), election.getCreatedAt(), election.getCreatedBy()));
        } else {
            return HttpResponse.createNotFound();
        }

    }

    //    @AuthorityRequired(authority = "VOTE_CREATE_FORM")
    @PostMapping
    public HttpResponse<?> createElection(ForwardedHttpServletRequest request, @RequestBody @Valid CreateElectionRequestData data) {
        if (electionRepository.findAllByName(data.getName()).isPresent()) {
            return HttpResponse.createConflict("Election with this name already exists");
        } else {
            Election election = new Election();
            Map<Integer, Party> parties = new HashMap<>();

            System.out.println(data.getParties());

            data.getParties().forEach((place, party) -> parties.put(place, party.getPartyEntity()));

            election.setName(data.getName());
            election.setParties(parties);
            election.setStartDateTime(data.getStartDateTime());
            election.setEndDateTime(data.getEndDateTime());
            election.setCreatedBy(request.getUserPrincipal().getId());
            election.setCreatedAt(Date.from(Instant.now()));

            election = this.electionRepository.save(election);

            return new HttpResponse<>(new CreateElectionResponseData(election.getId()));
        }
    }

    @DeleteMapping("/{id}")
    public HttpResponse<?> deleteElection(@PathVariable long id) {
        Optional<Election> optionalElection = electionRepository.findById(id);

        if (optionalElection.isPresent()) {
            electionRepository.delete(optionalElection.get());

            return HttpResponse.createOK();
        } else {
            return HttpResponse.createNotFound();
        }
    }

}
