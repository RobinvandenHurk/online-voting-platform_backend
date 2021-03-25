package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: Form
 */

@Entity
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Party> parties;
    @NotNull
    private Date startDateTime;
    @NotNull
    private Date endDateTime;
    @CreatedDate
    private Date createdAt;
    @CreatedBy
    private long createdBy;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date dateCreated) {
        this.createdAt = dateCreated;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

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

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public void addParty(Party party) {
        this.parties.add(party);
    }

    public void removeParty(Party party) {
        this.parties.remove(party);
    }
}
