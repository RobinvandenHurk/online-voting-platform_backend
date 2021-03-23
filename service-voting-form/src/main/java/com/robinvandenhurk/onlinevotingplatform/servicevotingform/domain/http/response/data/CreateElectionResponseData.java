package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data;

import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.HttpResponseData;

/**
 * Author:    Robin van den Hurk
 * Date:      23/03/2021
 * File name: CreateElectionResponseData
 */

public class CreateElectionResponseData extends HttpResponseData {

    private long id;

    public CreateElectionResponseData(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
