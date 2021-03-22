package com.robinvandenhurk.onlinevotingplatform.serviceparty.domain.http.request;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: UpdatePartyRequestData
 */

public class UpdatePartyRequestData {

    @NotEmpty
    private String name;
    private List<Integer> membersToRemove;
    private List<Integer> membersToAdd;

    public UpdatePartyRequestData() {
        this.membersToAdd = new ArrayList<>();
        this.membersToRemove = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getMembersToRemove() {
        return membersToRemove;
    }

    public void setMembersToRemove(List<Integer> membersToRemove) {
        this.membersToRemove = membersToRemove;
    }

    public List<Integer> getMembersToAdd() {
        return membersToAdd;
    }

    public void setMembersToAdd(List<Integer> membersToAdd) {
        this.membersToAdd = membersToAdd;
    }
}
