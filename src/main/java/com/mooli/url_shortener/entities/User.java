package com.mooli.url_shortener.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(unique = true)
    private String emailAddress;


    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getPasswordHash() { return passwordHash; }

    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public String getEmailAddress() { return emailAddress; }


}
