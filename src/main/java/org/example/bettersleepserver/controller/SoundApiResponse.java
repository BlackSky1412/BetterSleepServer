package org.example.bettersleepserver.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SoundApiResponse {
    private Long id;
    private String name;
    private String color; // Corrected field name
    private String duration; // Keep it as String

    private String slogan;
    private String author;
    private String urlSound;

    // Constructor
    public SoundApiResponse(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("color") String color,
            @JsonProperty("duration") String duration,
            @JsonProperty("slogan") String slogan,
            @JsonProperty("author") String author,
            @JsonProperty("urlSound") String urlSound
            ) { // Keep it as String
        this.id = id;
        this.name = name;
        this.color = color;
        this.duration = duration;
        this.slogan = slogan;
        this.author = author;
        this.urlSound = urlSound;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlSound() {
        return urlSound;
    }

    public void setUrlSound(String urlSound) {
        this.urlSound = urlSound;
    }
}
