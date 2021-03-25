package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.components.NullOrNotEmpty;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Candidate;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.Party;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.*;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: UpdateElectionRequestData
 */

public class UpdateElectionRequestData {

    @NullOrNotEmpty
    private String name;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date startDateTime;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date endDateTime;
    @Valid
    private List<UpdateElectionRequestDataSubUpdateParty> partiesToUpdate;
    @Valid
    private List<UpdateElectionRequestDataSubNewParty> partiesToAdd;
    private List<Long> partiesToRemove;

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

    public List<Long> getPartiesToRemove() {
        return partiesToRemove;
    }

    public void setPartiesToRemove(List<Long> partiesToRemove) {
        this.partiesToRemove = partiesToRemove;
    }

    public List<UpdateElectionRequestDataSubUpdateParty> getPartiesToUpdate() {
        return partiesToUpdate;
    }

    public void setPartiesToUpdate(List<UpdateElectionRequestDataSubUpdateParty> partiesToUpdate) {
        this.partiesToUpdate = partiesToUpdate;
    }

    public List<UpdateElectionRequestDataSubNewParty> getPartiesToAdd() {
        return partiesToAdd;
    }

    public void setPartiesToAdd(List<UpdateElectionRequestDataSubNewParty> partiesToAdd) {
        this.partiesToAdd = partiesToAdd;
    }

    public static class UpdateElectionRequestDataSubNewParty {
        @NotEmpty
        private String name;
        @Min(value = 1)
        private int number;
        @NotEmpty
        @Valid
        private List<UpdateElectionRequestDataSubNewCandidate> members;

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

        public List<UpdateElectionRequestDataSubNewCandidate> getMembers() {
            return members;
        }

        public void setMembers(List<UpdateElectionRequestDataSubNewCandidate> members) {
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

    public static class UpdateElectionRequestDataSubUpdateParty {

        @NotNull
        @Min(value = 1)
        private long id;
        @NullOrNotEmpty
        private String name;
        private int number;
        @Valid
        private List<UpdateElectionRequestDataSubNewCandidate> membersToAdd;
        @Valid
        private List<UpdateElectionRequestDataSubUpdateCandidate> membersToUpdate;
        private List<Long> membersToRemove;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<UpdateElectionRequestDataSubNewCandidate> getMembersToAdd() {
            return membersToAdd;
        }

        public void setMembersToAdd(List<UpdateElectionRequestDataSubNewCandidate> membersToAdd) {
            this.membersToAdd = membersToAdd;
        }

        public List<Long> getMembersToRemove() {
            return membersToRemove;
        }

        public void setMembersToRemove(List<Long> membersToRemove) {
            this.membersToRemove = membersToRemove;
        }

        public List<UpdateElectionRequestDataSubUpdateCandidate> getMembersToUpdate() {
            return membersToUpdate;
        }

        public void setMembersToUpdate(List<UpdateElectionRequestDataSubUpdateCandidate> membersToUpdate) {
            this.membersToUpdate = membersToUpdate;
        }
    }

    public static class UpdateElectionRequestDataSubNewCandidate {

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

    public static class UpdateElectionRequestDataSubUpdateCandidate {

        @Id
        @NotNull
        @Min(value = 1)
        private long id;
        private int number;
        @NullOrNotEmpty
        private String firstName;
        @NullOrNotEmpty
        private String lastName;
        @NullOrNotEmpty
        private String placeOfBirth;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

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
    }

}
