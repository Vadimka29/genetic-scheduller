package com.redkite.algorithm.model;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Schedule implements Serializable{
    protected List<Day> days;
    //optimization
    private Set<Day> daysWithTasks;
    private int numberOfTasks;
    private final Random random = new Random();
    protected final LocalDate start;
    protected final LocalDate end;

    public Schedule(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
        fillDays();
    }

    public Schedule(Semester semester) {
        this.start = semester.getStart();
        this.end = semester.getEnd();
        days = semester.getDays();
    }

    public void createScheduleForSemester(List<SubTask> subTasks) {
        daysWithTasks = new HashSet<>();
        numberOfTasks = subTasks.size();
        int index = random.nextInt(days.size());

        for (SubTask subTask : subTasks) {
            Day day = days.get(index);
            daysWithTasks.add(day);
            day.addTask(subTask);
            index = random.nextInt(days.size());

        }
    }

    public List<Day> getDays() {
        return new ArrayList<>(days);
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    private void fillDays() {
        int numberOfDays = (int) ChronoUnit.DAYS.between(start, end);
        LocalDate curr = start;
        days = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            days.add(new Day(curr));
            curr.plusDays(1);
        }
    }

    public List<SubTask> getAllScheduledSubTasks(){
        List<SubTask> allScheduleSubTasks = new ArrayList<>();
        days.forEach(day -> {
            allScheduleSubTasks.addAll(day.getSubTasks());
        });
        return allScheduleSubTasks;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Set<Day> getDaysWithTasks() {
        return daysWithTasks;
    }



    @Override
    public String toString() {
        return StringUtils.join(days.stream()
                .map(Day::toString)
                .collect(Collectors.toList()), "\n");
    }
}
