package com.redkite.algorithm.genetic;

import com.redkite.algorithm.model.Day;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Task;

import java.time.LocalDate;

/**
 * Created by Kirill Liubun on 27/04/2016.
 */
public class GenSchedule extends Schedule implements Chromosome<Schedule>  {

    public GenSchedule(LocalDate start, LocalDate end) {
        super(start, end);
    }

    @Override
    public Schedule doCrossover(Schedule chr) {
        return null;
    }

    @Override
    public void doMutation() {

    }

    @Override
    public double getFitness() {
        return 0;
    }


    //TODO write in functional style
    private Task findLastTaskInGroup(int groupId) {
        Task last = null;
        for (Day day : days) {
            for (Task task : day.getTasks()) {
                if (task.getGroupId() == groupId) {
                    if (last == null)
                        last = task;
                    if (task.getDate().isAfter(last.getDate()))
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
                if (task.getGroupId() == groupId) {
                    if (first == null)
                        first = task;
                    if (task.getDate().isBefore(first.getDate()))
                        first = task;
                }
            }
        }
        return first;
    }
}
