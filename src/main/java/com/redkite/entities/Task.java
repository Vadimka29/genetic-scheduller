package com.redkite.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="due_date")
    private LocalDate dueDate;

    @Column(name="created_date")
    private LocalDate createdDate;

    @Column(name="hours_day")
    private Integer hoursPerDay;

    @Column(name="duration")
    private Integer duration;

}
