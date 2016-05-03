package com.redkite.algorithm.model;


import java.time.LocalDate;

public class SubTask {
    private final long id;
    //task know about day when it should be done
    private LocalDate date;
    private final long groupId;
    private final String groupName;
    private final long duration;
    private int weight;

    public SubTask(long id, long groupId, long duration, String groupName) {
        this.id = id;
        this.groupId = groupId;
        this.duration = duration;
        this.groupName = groupName;
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


    @Override
    public String toString() {
        return String.format("Task[id=%d, name=\'%s\', dur=%.2f]", id, groupName, duration / (60 * 1000.0));
    }
}
