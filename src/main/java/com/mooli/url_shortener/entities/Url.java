package com.mooli.url_shortener.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "urls")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @Column(unique = true)
    private String shortUrl;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }

    public String getShortUrl(){ return shortUrl; }
}
