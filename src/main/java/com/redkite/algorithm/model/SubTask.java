package com.redkite.algorithm.model;


import com.redkite.entities.Task;

import java.time.LocalDate;

public class SubTask {
    private final long id;
    //task know about day when it should be done
    private LocalDate executionDate;
    private final Integer duration; // in hours
    private final Task parentTask;

    public SubTask(long id, Task parentTask, Integer duration) {
        this.id = id;
        this.parentTask = parentTask;
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public LocalDate getDate() {
        return executionDate;
    }

    public void setDate(LocalDate date) {
        this.executionDate = date;
    }



    @Override
    public String toString() {
        return String.format("Task[id=%d, name=\'%s\', dur=%.2f]", id, parentTask.getTaskName(), duration / (60 * 1000.0));
    }

    public String getParentName() {
        return parentTask.getTaskName();
    }
}
