package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Candidate;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Party;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: CreateFormRequest
 */

public class CreateElectionRequestData {

    @NotEmpty
    private String name;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date startDateTime;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date endDateTime;
    @NotEmpty
    private Map<Integer, CreateElectionRequestDataSubParty> parties;

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, CreateElectionRequestDataSubParty> getParties() {
        return parties;
    }

    public void setParties(Map<Integer, CreateElectionRequestDataSubParty> parties) {
        this.parties = parties;
    }

    public static class CreateElectionRequestDataSubParty {

        @NotEmpty
        private String name;
        @NotEmpty
        private Map<Integer, CreateElectionRequestDataSubCandidate> members;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<Integer, CreateElectionRequestDataSubCandidate> getMembers() {
            return members;
        }

        public void setMembers(Map<Integer, CreateElectionRequestDataSubCandidate> members) {
            this.members = members;
        }

        public Party getPartyEntity() {
            Party party = new Party();
            Map<Integer, Candidate> candidates = new HashMap<>();

            getMembers().forEach((place, candidate) -> candidates.put(place, candidate.getCandidateEntity()));

            party.setName(name);
            party.setMembers(candidates);

            return party;
        }
    }

    public static class CreateElectionRequestDataSubCandidate {

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

        public Candidate getCandidateEntity() {
            Candidate candidate = new Candidate();

            candidate.setFirstName(firstName);
            candidate.setLastName(lastName);
            candidate.setPlaceOfBirth(placeOfBirth);

            return candidate;
        }
    }

}
