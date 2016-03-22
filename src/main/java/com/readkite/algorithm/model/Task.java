package com.readkite.algorithm.model;


import java.time.LocalDate;

public class Task {
    private String name;
    //task know about day when it should be done
    private LocalDate date;
    private long groupId;
    private long duration;
    private int weight;

    public Task(String name, long groupId, long duration, int weight) {
        this.name = name;
        this.groupId = groupId;
        this.duration = duration;
        this.weight = weight;
    }

    public long getDuration() {
        return duration;
    }

    public int getWeight() {
        return weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getGroupId() {
        return groupId;
    }
}
