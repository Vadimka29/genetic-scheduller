package com.redkite.algorithm.impl;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;

import java.util.ArrayList;
import java.util.List;


public class GeneticAlgorithm implements Algorithm {
    private List<Schedule> population;
    private List<Schedule> bestChromosomes;
    private Integer populationSize = 30;
    private Integer iteration = 0;
    private Schedule initialSchedule;



    @Override
    public Schedule doCalculation(Semester semester, List<SubTask> subTasks) {
        createInitPopulation(semester, subTasks);
        return null;
    }

    @Override
    public double getInitialOptimizedValue() {
        return 0;
    }

    @Override
    public double getFinalOptimizedValue() {
        return 0;
    }


    private List<Schedule> chooseParents() {
        return null;
    }


    private List<Schedule> Selection() {
        return null;
    }


    private void createInitPopulation(Semester semester, List<SubTask> subTasks) {
        population = new ArrayList<>(populationSize);
        for (int i = 0; i < populationSize; i++) {
            Schedule schedule = new Schedule(semester, subTasks);
            population.add(schedule);
        }
    }

    @Override
    public void setInitialSchedule(Schedule initialSchedule) {
        this.initialSchedule = initialSchedule;
    }
}
