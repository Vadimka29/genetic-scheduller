package com.readkite.algorithm.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Day {
    private final LocalDate date;
    private List<Task> tasks;
    //amount of free minutes
    private final long dayLimit;
    //TODO find physical explanation
    private final int optimumCapacity;
    private Random random = new Random();


    public Day(LocalDate date, long dayLimit, int optimumCapacity) {
        this.date = date;
        this.dayLimit = dayLimit;
        this.optimumCapacity = optimumCapacity;
    }

    //hard criteria
    public boolean isEnoughTime() {
        return dayLimit >= tasks.stream().mapToLong(Task::getDuration).sum();
    }

    public boolean isOptimumCapacity() {
        return optimumCapacity >= tasks.stream().mapToInt(Task::getWeight).sum();
    }

    public void randomlyReplaceTask(Task task) {
        int index = random.nextInt(tasks.size() - 1);
        task.setDate(date);
        tasks.add(index, task);
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public boolean addTask(Task task) {
        task.setDate(date);
        return tasks.add(task);
    }



}
