package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: Party
 */

@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String name;
    @NotNull
    private int number;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Candidate> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<Candidate> getMembers() {
        return new ArrayList<>(members);
    }

    public void setMembers(List<Candidate> members) {
        this.members = members;
    }

    public void addMember(Candidate member) {
        this.members.add(member);
    }

    public void removeMember(Candidate member) {
        this.members.remove(member);
    }
}
