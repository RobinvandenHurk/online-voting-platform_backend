package com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain;

import javax.persistence.*;
import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: Form
 */

@Entity
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany
    private Map<Integer, Party> parties;

    public Form(String name, Map<Integer, Party> parties) {
        this.name = name;
        this.parties = parties;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Party> getParties() {
        return parties;
    }

}
