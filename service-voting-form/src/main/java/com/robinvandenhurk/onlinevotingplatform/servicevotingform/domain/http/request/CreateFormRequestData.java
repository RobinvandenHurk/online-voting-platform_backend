package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.request;

import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Party;
import com.sun.istack.NotNull;

import java.util.HashMap;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: CreateFormRequest
 */

public class CreateFormRequestData {

    @NotNull
    private String name;
    private HashMap<Integer, Party> parties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, Party> getParties() {
        return parties;
    }

    public void setParties(HashMap<Integer, Party> parties) {
        this.parties = parties;
    }
}
