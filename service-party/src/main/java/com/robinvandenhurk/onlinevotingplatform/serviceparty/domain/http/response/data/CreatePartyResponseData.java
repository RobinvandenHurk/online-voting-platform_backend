package com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.data;

import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.HttpResponseData;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: CreatePartyResponseData
 */

public class CreatePartyResponseData extends HttpResponseData {

    private long id;

    public CreatePartyResponseData(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
