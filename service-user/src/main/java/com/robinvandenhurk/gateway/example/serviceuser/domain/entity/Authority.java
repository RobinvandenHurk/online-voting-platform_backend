package com.robinvandenhurk.gateway.example.serviceuser.domain.entity;

import javax.persistence.*;

/**
 * Author:    Robin van den Hurk
 * Date:      15/03/2021
 * File name: Authority
 */

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public Authority() {

    }

    public Authority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
