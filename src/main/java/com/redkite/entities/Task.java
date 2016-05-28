package com.redkite.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.redkite.algorithm.model.SubTask;
import com.redkite.serializers.LocalDateDeserializer;
import com.redkite.serializers.LocalDateSerializer;
import lombok.Data;

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
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "task_name", unique = true)
    private String taskName;

    //TODO refactor type
    @Column(name = "priority")
    private Integer priority = 0;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "hours_day")
    private Integer hoursPerDay;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

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
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public void setCreatedDate(LocalDate createdDate){
        this.createdDate = createdDate;
    }

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public void setDeadline(LocalDate deadline){
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", createdDate=" + createdDate +
                ", duration=" + duration +
                '}';
    }
}
