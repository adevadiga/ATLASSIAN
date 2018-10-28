package com.anoop.cst.models;

import java.util.Objects;

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

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message m1 = (Message) obj;
        return Objects.equals(name, m1.getName()) && totalPoints == m1.getTotalPoints();
    }

    public String toString() {
        return "{" + this.name + "," + this.totalPoints + "}";
    }
}