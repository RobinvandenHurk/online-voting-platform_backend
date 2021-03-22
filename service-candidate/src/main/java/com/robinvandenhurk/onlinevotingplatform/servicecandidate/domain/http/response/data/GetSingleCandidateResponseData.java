package com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.response.data;

import com.robinvandenhurk.onlinevotingplatform.servicecandidate.domain.http.response.HttpResponseData;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: GetAllCandidatesResponse
 */

public class GetSingleCandidateResponseData extends HttpResponseData {

    private Long id;
    private String firstName;
    private String lastName;
    private String placeOfBirth;

    public GetSingleCandidateResponseData(Long id, String firstName, String lastName, String placeOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.placeOfBirth = placeOfBirth;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }
}
