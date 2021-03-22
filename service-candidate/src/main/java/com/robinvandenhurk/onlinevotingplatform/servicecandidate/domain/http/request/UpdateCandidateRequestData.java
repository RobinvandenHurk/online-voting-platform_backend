package com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.request;

import javax.validation.constraints.NotEmpty;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: CreateCandidateRequestData
 */

public class UpdateCandidateRequestData {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String placeOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
}
