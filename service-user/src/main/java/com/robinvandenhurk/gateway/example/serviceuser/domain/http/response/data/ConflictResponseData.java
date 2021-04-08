package com.robinvandenhurk.gateway.example.serviceuser.domain.http.response.data;

import com.robinvandenhurk.gateway.example.serviceuser.domain.http.response.HttpResponseData;

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
