package com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      12/03/2021
 * File name: User
 */

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private boolean isEnabled;
    @OneToMany
    private List<Authority> authorities;

    public User(String firstName, String lastName, String email, String passwordHash, boolean isEnabled, List<Authority> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.isEnabled = isEnabled;
        this.authorities = authorities;
    }

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}