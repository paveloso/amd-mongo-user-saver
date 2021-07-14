package com.teststation.mongousersaver.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "leaguePlayers")
public class Player implements Serializable {

    @Id
    private String id;

    @Indexed
    private String name;

    private String country;

    private int age;

    private Date creationDate;

    public Player(String id, String name, String country, int age, Date creationDate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.age = age;
        this.creationDate = creationDate;
    }

    public Player() {
    }
}
