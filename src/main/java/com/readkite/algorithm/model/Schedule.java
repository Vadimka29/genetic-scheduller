package com.readkite.algorithm.model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Schedule {
    private List<Day> days;
    //optimization
    private Set<Day> daysWithTasks;
    private int numberOfTasks;
    private Random random = new Random();

    public void createScheduleBySemester(LocalDate start, LocalDate end, List<Task> tasks) {
        int numberOfDays = (int) ChronoUnit.DAYS.between(end, start);
        numberOfTasks = tasks.size();
        int number = 3;
        int index = random.nextInt(days.size() - 1);
        days = new ArrayList<>(numberOfDays);
        daysWithTasks = new HashSet<>();

        for (Task task : tasks) {
            Day day = days.get(index);
            daysWithTasks.add(day);
            day.addTask(task);

            if(number > 0) {
                number--;
            } else {
                number = random.nextInt(5);
                index = random.nextInt(days.size() - 1);
            }
        }
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }


    public Schedule doCrossover(Schedule schedule) {
        return null;
    }

    public void doMutation() {

    }

    //TODO write in functional style
    private Task findLastTaskInGroup(int groupId) {
        Task last = null;
        for (Day day : days) {
            for (Task task : day.getTasks()) {
                if(task.getGroupId() == groupId) {
                    if(last == null)
                        last = task;
                    if(task.getDate().isAfter(last.getDate()))
                        last = task;

                }
            }
        }
        return last;
    }

    public double calculateFitness() {
        return 0;
    }

    private Task findFirstTaskInGroup(int groupId) {
        Task first = null;
        for (Day day : days) {
            for (Task task : day.getTasks()) {
                if(task.getGroupId() == groupId) {
                    if(first == null)
                        first = task;
                    if(task.getDate().isBefore(first.getDate()))
                        first = task;
                }
            }
        }
        return first;
    }

}
