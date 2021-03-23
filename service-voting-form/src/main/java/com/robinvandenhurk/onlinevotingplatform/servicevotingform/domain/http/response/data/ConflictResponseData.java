package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.data;

import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.HttpResponseData;

import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: BadRequestResponseData
 */

public class ConflictResponseData extends HttpResponseData {

    private String message;

    public ConflictResponseData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
