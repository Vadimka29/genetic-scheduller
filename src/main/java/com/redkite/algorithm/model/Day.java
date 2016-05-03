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
    private List<SubTask> subTasks;
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
        this.subTasks = new ArrayList<>();
    }

    //hard criteria
    public boolean isEnoughTime() {
        return dayLimit >= subTasks.stream().mapToLong(SubTask::getDuration).sum();
    }

    public boolean isOptimumCapacity() {
        return optimumCapacity >= subTasks.stream().mapToInt(SubTask::getWeight).sum();
    }

    public void randomlyReplaceTask(SubTask subTask) {
        int index = random.nextInt(subTasks.size() - 1);
        subTask.setDate(date);
        subTasks.add(index, subTask);
    }

    public LocalDate getDate() {
        return date;
    }

    public List<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks);
    }

    public boolean addTask(SubTask subTask) {
        subTask.setDate(date);
        return subTasks.add(subTask);
    }


    @Override
    public String toString() {
        List<String> taskList = subTasks.stream()
                .map(SubTask::toString)
                .collect(Collectors.toList());
        String tasks = taskList.isEmpty() ? "Free Day" : StringUtils.join(taskList, "\n\t");
        return "Day[" + dtf.format(date) + "]\n\t" + tasks;

    }
}
