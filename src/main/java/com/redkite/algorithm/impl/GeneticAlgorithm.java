package com.redkite.algorithm.impl;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.geneticmodel.GenSchedule;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GeneticAlgorithm implements Algorithm {
    private List<GenSchedule> population;
    private double bestValue = 0;
    private Integer populationSize = 30;
    private Integer iteration = 0;
    private Integer maxIterations = 50;
    private double mutationProbability = 0.5;
    private final Random random = new Random();

    @Override
    public Schedule doCalculation(Semester semester, List<SubTask> subTasks) {
        createInitPopulation(semester, subTasks);
        for (int i = 0; i < maxIterations; i++) {
            iteration = i;
            List<GenSchedule> parents = chooseParents();
            for (int j = 0; j < parents.size(); j += 2) {
                population.add((GenSchedule) parents.get(j).doCrossover(parents.get(j + 1)));
            }
            population = selectNewGeneration();
            if(mutationProbability <= random.nextDouble()) {
                GenSchedule schedule = population.get(random.nextInt(population.size()));
                schedule.doMutation();
            }

        }
        Collections.sort(population, (o1, o2) -> (int) (o2.getFitness() - o1.getFitness()));
        bestValue = population.get(0).getFitness();
        return population.get(0);
    }

    @Override
    public double getInitialOptimizedValue() {
        return 0;
    }

    @Override
    public double getFinalOptimizedValue() {
        return bestValue;
    }


    private List<GenSchedule> chooseParents() {
        int size = population.size();
        int parentsNumber = size % 2 == 0 ? size / 2 : (size - 1) / 2;
        List<GenSchedule> parents = new ArrayList<>();
        double sum = population.stream().mapToDouble(GenSchedule::getFitness).sum();
        for (int i = 0; i < parentsNumber; i++) {
            int r = random.nextInt((int) sum);
            double partSum = 0;
            int chosenChr = 0;
            for (int j = 0; j < size; j++) {
                partSum += population.get(j).getFitness();
                if (partSum > r) {
                    chosenChr = j;
                    break;
                }
            }
            parents.add(population.get(chosenChr));
        }
        return parents;
    }


    private List<GenSchedule> selectNewGeneration() {
        int size = population.size();
        Collections.sort(population, (o1, o2) -> (int) (o1.getFitness() - o2.getFitness()));
        return population.subList(size / 2, size);
    }


    private void createInitPopulation(Semester semester, List<SubTask> subTasks) {
        population = new ArrayList<>(populationSize);
        for (int i = 0; i < populationSize; i++) {
            GenSchedule schedule = new GenSchedule(semester, subTasks);
            population.add(schedule);
        }
    }


}
