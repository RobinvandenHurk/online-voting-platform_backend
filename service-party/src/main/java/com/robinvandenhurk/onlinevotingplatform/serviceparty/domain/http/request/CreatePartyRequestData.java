package com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.request;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: CreatePartyRequestData
 */

public class CreatePartyRequestData {

    @NotEmpty
    private String name;
    private List<Integer> members;

    public CreatePartyRequestData() {
        this.members = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }
}
