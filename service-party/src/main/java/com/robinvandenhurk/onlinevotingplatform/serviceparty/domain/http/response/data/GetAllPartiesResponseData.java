package com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.data;


import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.Party;
import com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.response.HttpResponseData;

import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: GetAllPartiesResponseData
 */

public class GetAllPartiesResponseData extends HttpResponseData {

    private List<Party> parties;

    public GetAllPartiesResponseData(List<Party> parties) {
        this.parties = parties;
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }
}
