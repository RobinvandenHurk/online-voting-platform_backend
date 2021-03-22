package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: Party
 */

@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String name;
    @OneToMany
    private Map<Integer, Candidate> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Candidate> getMembers() {
        return new HashMap<>(members);
    }

    public void setMembers(HashMap<Integer, Candidate> members) {
        this.members = members;
    }
}
