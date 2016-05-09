package com.redkite.algorithm.impl;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;

import java.util.List;


public class GeneticAlgorithm implements Algorithm {
    private List<Schedule> population;
    private List<Schedule> bestChromosomes;


    @Override
    public Schedule doCalculation(Semester semester, List<SubTask> subTasks) {

        System.out.println("geneticAlgorithm");
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


    private void createPopulation() {

    }
}
