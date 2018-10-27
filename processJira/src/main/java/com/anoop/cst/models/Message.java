package com.anoop.cst.models;

public class Message {
    private String name;
    private Integer totalPoints;

    public Message(String name, Integer totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }
}