package com.robinvandenhurk.onlinevotingplatform.serviceparty.controllers;


import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.Party;
import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.request.CreatePartyRequestData;
import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.request.UpdatePartyRequestData;
import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.HttpResponse;
import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.data.CreatePartyResponseData;
import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.data.GetAllPartiesResponseData;
import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.data.GetSinglePartyResponseData;
import com.robinvandenhurk.onlinevotingplatform.serviceparty.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: PartyController
 */

@RestController
@RequestMapping("/party")
public class PartyController {

    private PartyRepository partyRepository;

    @Autowired
    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping
    public HttpResponse<GetAllPartiesResponseData> getAllParties() {
        List<Party> candidates = partyRepository.findAll();

        return new HttpResponse<>(new GetAllPartiesResponseData(candidates));
    }

    @GetMapping("/{id}")
    public HttpResponse<?> getParty(@PathVariable long id) {
        Optional<Party> optionalParty = partyRepository.findById(id);

        if (optionalParty.isPresent()) {
            Party party = optionalParty.get();

            return new HttpResponse<>(new GetSinglePartyResponseData(party.getId(), party.getName()));
        } else {
            return HttpResponse.createNotFound();
        }
    }

    @PostMapping
    public HttpResponse<CreatePartyResponseData> createParty(@RequestBody @Validated CreatePartyRequestData data) {
        Party party = new Party();

        party.setName(data.getName());
        party.setMembers(data.getMembers());

        party = partyRepository.save(party);

        return new HttpResponse<>(new CreatePartyResponseData(party.getId()));
    }

    @PutMapping("/{id}")
    public HttpResponse<?> UpdateParty(@PathVariable long id, @RequestBody @Validated UpdatePartyRequestData data) {
        Optional<Party> optionalParty = partyRepository.findById(id);

        if (optionalParty.isPresent()) {
            Party party = optionalParty.get();

            party.setName(data.getName());
            data.getMembersToRemove().forEach(party::removeMember);
            data.getMembersToAdd().forEach(party::addMember);

            partyRepository.save(party);

            return HttpResponse.createOK();
        } else {
            return HttpResponse.createNotFound();
        }
    }

    @DeleteMapping("/{id}")
    public HttpResponse<?> DeleteCandidate(@PathVariable long id) {
        Optional<Party> optionalParty = partyRepository.findById(id);

        if (optionalParty.isPresent()) {
            partyRepository.delete(optionalParty.get());

            return HttpResponse.createOK();
        } else {
            return HttpResponse.createNotFound();
        }
    }
}
