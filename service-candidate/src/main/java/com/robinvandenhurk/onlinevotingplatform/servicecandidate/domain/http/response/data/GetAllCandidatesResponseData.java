package com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.response.data;

import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.Candidate;
import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.response.HttpResponseData;

import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: GetAllCandidatesResponse
 */

public class GetAllCandidatesResponseData extends HttpResponseData {

    private List<Candidate> candidates;

    public GetAllCandidatesResponseData(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
