package com.robinvandenhurk.gateway.example.serviceauthorization.domain.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Author:    Robin van den Hurk
 * Date:      12/03/2021
 * File name: User
 */

@Entity
public class Credentials {
    @Id
//    Credentials get created when a User Creation Message is received, which includes the user ID
    private Long userId;
    private String email;
    private String passwordHash;
    private boolean isEnabled;
    @ManyToMany
    private List<Authority> authorities;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}