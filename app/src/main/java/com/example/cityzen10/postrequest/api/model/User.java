package com.example.cityzen10.postrequest.api.model;

public class User {
    public Integer id;
    private String name;
    private String job;

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }
}
