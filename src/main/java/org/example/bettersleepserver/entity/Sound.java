package org.example.bettersleepserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "sound")
public class Sound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String color;
    String duration;
    String slogan;
    String author;
    String urlSound;

}


