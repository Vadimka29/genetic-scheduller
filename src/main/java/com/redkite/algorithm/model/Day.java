package com.redkite.algorithm.model;

import com.redkite.utils.TaskUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Day implements Serializable {
    private final LocalDate date;
    private List<SubTask> subTasks;
    //amount of free hours
    private int freeTime;
    //TODO find physical explanation
    private final int optimumCapacity;
    // how many hours man can work every day without hurt to his health
    private final int MAX_DAY_LIMIT = 12;
    private Random random = new Random();


    /**
     * Create day with passed date with 6h hours limit
     */
    public Day(LocalDate date) {
        this(date, 6, 0);
    }


    public Day(LocalDate date, int freeTime) {
        this(date, freeTime, 0);
    }

    public Day(LocalDate date, int freeTime, int optimumCapacity) {
        this.date = date;
        this.freeTime = freeTime;
        this.optimumCapacity = optimumCapacity;
        this.subTasks = new ArrayList<>();
    }

    //hard criteria
    public boolean isEnoughTime() {
        return freeTime >= subTasks.stream().mapToLong(SubTask::getDuration).sum();
    }


    public void randomlyMoveSubTask(SubTask subTask, Day fromDay) {
        fromDay.getSubTasks().remove(subTask);
        if (!subTasks.isEmpty()) {
            int index = random.nextInt(subTasks.size());
            subTask.setExecutionDate(date);
            subTasks.add(index, subTask);
        } else {
            subTasks.add(subTask);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public boolean addTask(SubTask subTask) {
        subTask.setExecutionDate(date);
        return subTasks.add(subTask);
    }

    public void changeLimit(Integer freeTime) {
        if (freeTime < 24 && freeTime > -1) {
            freeTime = freeTime;
        } else {
            throw new IllegalArgumentException("Incorrect number of hours, number can't be greater than 24 or negative");
        }
    }

    /**
     * Return work load percent of this day
     */
    public double getDayWorkLoad() {
        int subTasksSummTime = subTasks.stream().mapToInt(SubTask::getDuration).sum();
        return Math.abs((freeTime - subTasksSummTime) / (double) freeTime);
    }


    public int getLeftFreeTimeForDay() {
        int subTasksSummTime = subTasks.stream().mapToInt(SubTask::getDuration).sum();
        return MAX_DAY_LIMIT - subTasksSummTime;
    }

    public int getFreeTime() {
        return freeTime;
    }

    @Override
    public String toString() {
        List<String> taskList = subTasks.stream()
                .map(SubTask::toString)
                .collect(Collectors.toList());
        String tasks = taskList.isEmpty() ? "Free Day" : StringUtils.join(taskList, "\n\t");
        return "Day[" + TaskUtils.getDateTimeFormatter().format(date) + ", " + freeTime
                + "]\n\t" + tasks;

    }
}
