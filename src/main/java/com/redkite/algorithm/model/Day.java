package com.redkite.algorithm.model;


import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Day {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final LocalDate date;
    private List<Task> tasks;
    //amount of free minutes
    private final long dayLimit;
    //TODO find physical explanation
    private final int optimumCapacity;
    private Random random = new Random();


    public Day(LocalDate date) {
        this(date, 6 * ChronoUnit.HOURS.getDuration().toMillis(), 0);
    }

    public Day(LocalDate date, long dayLimit, int optimumCapacity) {
        this.date = date;
        this.dayLimit = dayLimit;
        this.optimumCapacity = optimumCapacity;
        this.tasks = new ArrayList<>();
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


    @Override
    public String toString() {
        List<String> taskList = tasks.stream()
                .map(Task::toString)
                .collect(Collectors.toList());
        String tasks = taskList.isEmpty() ? "Free Day" : StringUtils.join(taskList, "\n\t");
        return "Day[" + dtf.format(date) + "]\n\t" + tasks;

    }
}
