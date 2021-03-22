package com.robinvandenhurk.onlinevotingplatform.servicevotingform.controllers;

import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Form;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.request.CreateFormRequestData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.GetFormsData;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.HttpResponse;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.repositories.CandidateRepository;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.repositories.FormRepository;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: VoteFormController
 */

@RestController()
@RequestMapping("/voting-form")
public class VoteFormController {

    private PartyRepository partyRepository;
    private CandidateRepository candidateRepository;
    private FormRepository formRepository;

    @Autowired
    public VoteFormController(PartyRepository partyRepository, CandidateRepository candidateRepository, FormRepository formRepository) {
        this.partyRepository = partyRepository;
        this.candidateRepository = candidateRepository;
        this.formRepository = formRepository;
    }

    //    @AuthorityRequired(authority = "VOTE_CREATE_FORM")
    @PostMapping
    public ResponseEntity<HttpResponse> createForm(@RequestBody @Validated CreateFormRequestData data) {
        Form form = new Form(data.getName(), data.getParties());

        this.formRepository.save(form);

        return ResponseEntity.ok(new HttpResponse(new GetFormsData(this.formRepository.findAll())));
    }

    @GetMapping
    public ResponseEntity<HttpResponse> getForms() {
        return ResponseEntity.ok(new HttpResponse(new GetFormsData(this.formRepository.findAll())));
    }

}
