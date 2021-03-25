package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Candidate;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Party;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

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
    @Valid
    private List<CreateElectionRequestDataSubParty> parties;

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

    public List<CreateElectionRequestDataSubParty> getParties() {
        return parties;
    }

    public void setParties(List<CreateElectionRequestDataSubParty> parties) {
        this.parties = parties;
    }

    public static class CreateElectionRequestDataSubParty {

        @Min(value = 1)
        private int number;
        @NotEmpty
        private String name;
        @NotEmpty
        @Valid
        private List<CreateElectionRequestDataSubCandidate> members;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CreateElectionRequestDataSubCandidate> getMembers() {
            return members;
        }

        public void setMembers(List<CreateElectionRequestDataSubCandidate> members) {
            this.members = members;
        }

        public Party getPartyEntity() {
            Party party = new Party();
            List<Candidate> candidates = new ArrayList<>();

            getMembers().forEach(candidate -> candidates.add(candidate.getCandidateEntity()));

            party.setNumber(number);
            party.setName(name);
            party.setMembers(candidates);

            return party;
        }
    }

    public static class CreateElectionRequestDataSubCandidate {

        @Min(value = 1)
        private int number;
        @NotEmpty
        private String firstName;
        @NotEmpty
        private String lastName;
        @NotEmpty
        private String placeOfBirth;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

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

            candidate.setNumber(number);
            candidate.setFirstName(firstName);
            candidate.setLastName(lastName);
            candidate.setPlaceOfBirth(placeOfBirth);

            return candidate;
        }
    }

}
