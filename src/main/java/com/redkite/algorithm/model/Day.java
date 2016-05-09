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
    private Random random = new Random();


    /**
    * Create day with passed date with 6h hours limit
    * */
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
        if(!subTasks.isEmpty()) {
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
        if(freeTime < 24 && freeTime > -1) {
            freeTime = freeTime;
        } else {
            throw new IllegalArgumentException("Incorrect number of hours, number can't be greater than 24 or negative");
        }
    }

    public double getDayWorkLoad(){
        int workloadHoursSumm = 0;
        for (SubTask subTask : subTasks) {
            workloadHoursSumm += subTask.getDuration();
        }
        return Math.abs((freeTime - workloadHoursSumm)/ (double) freeTime);
    }

    public int getLeftFreeTimeForDay(){
        int subTasksSummTime = 0;
        for (SubTask subTask : subTasks) {
            subTasksSummTime += subTask.getDuration();
        }
        return 12 - subTasksSummTime;
    }

    public int getFreeTime(){
        return freeTime;
    }

    @Override
    public String toString() {
        List<String> taskList = subTasks.stream()
                .map(SubTask::toString)
                .collect(Collectors.toList());
        String tasks = taskList.isEmpty() ? "Free Day" : StringUtils.join(taskList, "\n\t");
        return "Day[" + TaskUtils.getDateTimeFormatter().format(date) + ", free time: " + freeTime
                + "]\n\t" + tasks;

    }
}
