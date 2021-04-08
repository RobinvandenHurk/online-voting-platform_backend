package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data;

import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.HttpResponseData;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: ForbiddenResponseData
 */

public class ForbiddenResponseData extends HttpResponseData {

    private String message;

    public ForbiddenResponseData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
