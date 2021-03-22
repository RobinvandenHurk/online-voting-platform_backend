package com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.response.data;

import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.response.HttpResponseData;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: CreateCandidateResponseData
 */

public class CreateCandidateResponseData extends HttpResponseData {

    private long id;

    public CreateCandidateResponseData(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
