package com.redkite.entities;

import com.redkite.algorithm.model.SubTask;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class Task {

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

}
