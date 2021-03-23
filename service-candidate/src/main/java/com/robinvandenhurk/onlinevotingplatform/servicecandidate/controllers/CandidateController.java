package com.robinvandenhurk.onlinevotingplatform.servicecandidate.controllers;

import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.Candidate;
import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.request.CreateCandidateRequestData;
import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.request.UpdateCandidateRequestData;
import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.response.*;
import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.response.data.*;
import com.robinvandenhurk.onlinevotingplatform.servicecandidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: CandidateController
 */

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @GetMapping
    public HttpResponse<GetAllCandidatesResponseData> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();

        return new HttpResponse<>(new GetAllCandidatesResponseData(candidates));
    }

    @GetMapping("/{id}")
    public HttpResponse<?> getCandidate(@PathVariable long id) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);

        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();

            return new HttpResponse<>(new GetSingleCandidateResponseData(candidate.getId(), candidate.getFirstName(), candidate.getLastName(), candidate.getPlaceOfBirth()));
        } else {
            return HttpResponse.createNotFound();
        }
    }

    @PostMapping
    public HttpResponse<CreateCandidateResponseData> createCandidate(@RequestBody @Valid CreateCandidateRequestData data) {
        Candidate candidate = new Candidate();
        candidate.setFirstName(data.getFirstName());
        candidate.setLastName(data.getLastName());
        candidate.setPlaceOfBirth(data.getPlaceOfBirth());

        candidate = candidateRepository.save(candidate);

        return new HttpResponse<>(new CreateCandidateResponseData(candidate.getId()));
    }

    @PutMapping("/{id}")
    public HttpResponse<?> UpdateCandidate(@PathVariable long id, @RequestBody @Valid UpdateCandidateRequestData data) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);

        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();

            candidate.setFirstName(data.getFirstName());
            candidate.setLastName(data.getLastName());
            candidate.setPlaceOfBirth(data.getPlaceOfBirth());

            candidateRepository.save(candidate);

            return HttpResponse.createOK();
        } else {
            return HttpResponse.createNotFound();
        }
    }

    @DeleteMapping("/{id}")
    public HttpResponse<?> DeleteCandidate(@PathVariable long id) {
        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);

        if (optionalCandidate.isPresent()) {
            candidateRepository.delete(optionalCandidate.get());

            return HttpResponse.createOK();
        } else {
            return HttpResponse.createNotFound();
        }
    }
}
