package com.robinvandenhurk.onlinevotingplatform.servicevotingform.controllers;

import com.robinvandenhurk.gateway.library.userinjection.ForwardedHttpServletRequest;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Candidate;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Election;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Party;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.request.CreateElectionRequestData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.request.UpdateElectionRequestData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.HttpResponse;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data.CreateElectionResponseData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data.GetAllElectionsResponseData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data.GetSingleElectionResponseData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.repositories.CandidateRepository;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.repositories.ElectionRepository;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.repositories.PartyRepository;
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
    private PartyRepository partyRepository;
    private CandidateRepository candidateRepository;

    @Autowired
    public ElectionController(ElectionRepository electionRepository, PartyRepository partyRepository, CandidateRepository candidateRepository) {
        this.electionRepository = electionRepository;
        this.partyRepository = partyRepository;
        this.candidateRepository = candidateRepository;
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
            List<Party> parties = new ArrayList<>();

            data.getParties().forEach(party -> parties.add(party.getPartyEntity()));

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

    @PutMapping("/{id}")
    private HttpResponse<?> updateElection(@PathVariable long id, @RequestBody @Valid UpdateElectionRequestData data) {
        Optional<Election> optionalElection = electionRepository.findById(id);
        Map<String, String> errors = new HashMap<>();

        if (optionalElection.isEmpty()) {
            return HttpResponse.createNotFound();
        }

        Election election = optionalElection.get();
        Optional<Election> electionByName = electionRepository.findAllByName(data.getName());

//        Check if other election exists with the new name
        if (electionByName.isPresent() && electionByName.get() != election) {
            return HttpResponse.createConflict("Election with this name already exists");
        }

        if (data.getName() != null) election.setName(data.getName());

//        Update existing parties
        if (data.getPartiesToUpdate() != null) {
            data.getPartiesToUpdate().forEach(partyData -> {
                Optional<Party> optionalParty = partyRepository.findById(partyData.getId());

                if (optionalParty.isPresent()) {
                    Party party = optionalParty.get();

//                Check if the party exists in the election
                    if (election.getParties().contains(party)) {
//                    All is good. Update the party
                        if (partyData.getName() != null) {
                            party.setName(partyData.getName());
                        }
                        if (partyData.getMembersToAdd() != null) {
                            partyData.getMembersToAdd().forEach(member -> party.addMember(member.getCandidateEntity()));
                        }
                        if (partyData.getMembersToRemove() != null) {
                            partyData.getMembersToRemove().forEach(memberId -> {
                                Optional<Candidate> optionalCandidate = candidateRepository.findById(memberId);

                                if (optionalCandidate.isPresent()) {
                                    Candidate candidate = optionalCandidate.get();

                                    if (party.getMembers().contains(candidate)) {
                                        party.removeMember(candidate);

//                                    Return without logging unknown member error
                                        return;
                                    }
                                }

                                errors.put("unknownMember", "Member with id " + id + " does not exist in party " + party.getId());
                            });
                        }
                        if (partyData.getMembersToUpdate() != null) {
                            partyData.getMembersToUpdate().forEach(memberData -> {
                                Optional<Candidate> optionalCandidate = candidateRepository.findById(memberData.getId());

                                if (optionalCandidate.isPresent()) {
                                    Candidate candidate = optionalCandidate.get();

                                    if (party.getMembers().contains(candidate)) {
                                        if (memberData.getFirstName() != null)
                                            candidate.setFirstName(memberData.getFirstName());
                                        if (memberData.getLastName() != null)
                                            candidate.setFirstName(memberData.getLastName());
                                        if (memberData.getPlaceOfBirth() != null)
                                            candidate.setPlaceOfBirth(memberData.getPlaceOfBirth());
                                        if (memberData.getNumber() > 0) candidate.setNumber(memberData.getNumber());

//                                    Return without logging unknown member error
                                        return;
                                    }
                                }

                                errors.put("unknownMember", "Member with id " + id + " does not exist in party " + party.getId());
                            });
                        }
//                    Return without logging unknown party error
                        return;
                    }
                }

                errors.put("unknownParty", "Party with id " + partyData.getId() + " does not exist in this election");
            });
        }

//        Delete existing parties
        if (data.getPartiesToRemove() != null) {
            data.getPartiesToRemove().forEach(partyId -> {
                Optional<Party> optionalParty = partyRepository.findById(partyId);

                if (optionalParty.isPresent()) {
                    Party party = optionalParty.get();

                    election.removeParty(party);

//                Return without logging unknown party error
                    return;
                }

                errors.put("unknownParty", "Party with id " + partyId + " does not exist in this election");
            });
        }

//        Add new parties
        if (data.getPartiesToAdd() != null) {
            data.getPartiesToAdd().forEach(partyData -> election.addParty(partyData.getPartyEntity()));
        }

        if (errors.size() > 0) {
            return HttpResponse.createBadRequest("Invalid parameters received", errors);
        } else {
            electionRepository.save(election);

            return HttpResponse.createOK();
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
