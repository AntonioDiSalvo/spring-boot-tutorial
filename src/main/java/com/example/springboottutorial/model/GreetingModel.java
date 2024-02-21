package com.example.springboottutorial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GreetingModel {
    public long getId() {
        return id;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Id
    private long id;

    private String greeting;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
