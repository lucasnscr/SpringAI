package com.investor.investorcore.model;

import com.investor.investorcore.enums.UserProfile;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;

@Node("User")
public class User {

    @Id
    private final String id;
    private final String name;
    private final String email;
    private LocalDate createdAt;
    private UserProfile userProfile;

    public User(String id, String name, String email, LocalDate createdAt, UserProfile userProfile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.userProfile = userProfile;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}