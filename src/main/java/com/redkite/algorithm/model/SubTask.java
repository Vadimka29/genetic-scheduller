package com.redkite.algorithm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.redkite.entities.Task;
import com.redkite.serializers.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;

public class SubTask implements Serializable{
    private final long id;
    //task know about day when it should be done
    private LocalDate executionDate;
    private final Integer duration; // in hours
    private final Task parentTask;
    
    //TODO: (review by vadym): substask execution startLocalTime and execution endLocalTime need to be added to determine
    //the subtask performing timeline.

    public SubTask(long id, Task parentTask, Integer duration) {
        this.id = id;
        this.parentTask = parentTask;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }


    @JsonSerialize(using = LocalDateSerializer.class)
    public void setExecutionDate(LocalDate executionDate){
        this.executionDate = executionDate;
    }

    public void setDate(LocalDate date) {
        this.executionDate = date;
    }



    @Override
    public String toString() {
        return String.format("Task[id=%d, name=\'%s\', dur=%d]", id, parentTask.getTaskName(), duration);
    }

    public String getParentName() {
        return parentTask.getTaskName();
    }

    public Task getParentTask(){
        return parentTask;
    }

    public long getId() {
        return id;
    }
}
