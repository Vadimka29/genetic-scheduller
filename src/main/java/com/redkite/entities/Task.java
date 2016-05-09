package com.redkite.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.api.client.json.Json;
import com.redkite.algorithm.model.SubTask;
import com.redkite.serializers.LocalDateSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class Task implements Serializable {
    private static final int MAX_PRIORITY = 25;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "task_name")
    private String taskName;

    //TODO refactor type
    @Column(name = "priority")
    private Integer priority;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "hours_day")
    private Integer hoursPerDay;

    @Column(name = "duration")
    private Integer duration;

    public List<SubTask> toSubTasks() {
        List<SubTask> subTasks = new ArrayList<>();
        int amount = duration / hoursPerDay;
        int rest = duration % hoursPerDay;

        if(rest != 0) {
            amount++;
        }

        for (int i = 0; i < amount; i++) {
            if(i == amount - 1 && rest != 0) {
                subTasks.add( new SubTask(i, this, rest));
            } else {
                subTasks.add(new SubTask(i, this, hoursPerDay));
            }
        }

        return subTasks;
    }

    @JsonSerialize(using = LocalDateSerializer.class)
    public void setCreatedDate(LocalDate createdDate){
        this.createdDate = createdDate;
    }

    @JsonSerialize(using = LocalDateSerializer.class)
    public void setDeadline(LocalDate deadline){
        this.deadline = deadline;
    }

}
