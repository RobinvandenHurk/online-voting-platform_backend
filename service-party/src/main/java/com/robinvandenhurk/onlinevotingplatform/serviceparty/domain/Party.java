package com.robinvandenhurk.onlinevotingplatform.serviceparty.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ElementCollection
    private List<Integer> members;

    public Party() {
        this.members = new ArrayList<>();
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

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public void addMember(int id) {
        this.members.add(id);
    }

    public void removeMember(int id) {
        this.members.remove((Object) id);
    }
}
