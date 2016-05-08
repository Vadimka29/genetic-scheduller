package com.redkite.algorithm.geneticmodel;

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


    public double calculateFitness() {
        return 0;
    }


}
