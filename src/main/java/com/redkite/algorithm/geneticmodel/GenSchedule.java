package com.redkite.algorithm.geneticmodel;

import com.redkite.algorithm.geneticmodel.Chromosome;
import com.redkite.algorithm.model.Day;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.SubTask;

import java.time.LocalDate;

public class GenSchedule extends Schedule implements Chromosome<Schedule> {

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
    private SubTask findLastTaskInGroup(String name) {
        SubTask last = null;
        for (Day day : days) {
            for (SubTask subTask : day.getSubTasks()) {
                if (subTask.getParentName().equals(name)) {
                    if (last == null)
                        last = subTask;
                    if (subTask.getDate().isAfter(last.getDate()))
                        last = subTask;

                }
            }
        }
        return last;
    }

    public double calculateFitness() {
        return 0;
    }

    private SubTask findFirstTaskInGroup(String name) {
        SubTask first = null;
        for (Day day : days) {
            for (SubTask subTask : day.getSubTasks()) {
                if (subTask.getParentName().equals(name)) {
                    if (first == null)
                        first = subTask;
                    if (subTask.getDate().isBefore(first.getDate()))
                        first = subTask;
                }
            }
        }
        return first;
    }
}
