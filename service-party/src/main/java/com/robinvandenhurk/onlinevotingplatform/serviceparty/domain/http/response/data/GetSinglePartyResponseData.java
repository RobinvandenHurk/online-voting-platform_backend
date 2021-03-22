package com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.data;

import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.HttpResponseData;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: GetAllCandidatesResponse
 */

public class GetSinglePartyResponseData extends HttpResponseData {

    private Long id;
    private String name;

    public GetSinglePartyResponseData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
