package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data;

import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Election;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.HttpResponseData;

import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      23/03/2021
 * File name: GetAllElectionsResponseData
 */

public class GetAllElectionsResponseData extends HttpResponseData {

    private List<GetSingleElectionResponseData> elections;

    public GetAllElectionsResponseData(List<GetSingleElectionResponseData> elections) {
        this.elections = elections;
    }

    public List<GetSingleElectionResponseData> getElections() {
        return elections;
    }
}
