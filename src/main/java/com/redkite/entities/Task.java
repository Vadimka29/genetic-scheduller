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
            subTasks.add(new SubTask(i, this, hoursPerDay));
            //TODO: (review by Vadym): Если rest == 0 (т.е. duration нацело делится на hoursPerDay), тогда
            // тогда последняя сабтаска постоянно будет создавать с duration == 0. Нужно проверять была ли добавлена 1-ца к amount и
            //только если была добавлена 1-ца, тогда создавать последнюю subtask с duration = rest
            if(i == amount -1) {
                subTasks.add( new SubTask(i, this, rest));
            }
        }

        return subTasks;
    }

}
