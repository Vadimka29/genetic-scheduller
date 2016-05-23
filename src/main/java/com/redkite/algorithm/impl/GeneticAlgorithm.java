package com.redkite.algorithm.impl;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.ChartDataSuit;
import com.redkite.algorithm.geneticmodel.GenSchedule;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;
import com.redkite.entities.chart.ChartData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GeneticAlgorithm implements Algorithm, ChartDataSuit {
    private List<GenSchedule> population;
    private final double crossoverSize = 0.5;
    private final Integer populationSize = 20;
    private Integer iteration = 0;
    private final Integer maxIterations = 3000;
    private final double mutationProbability = 0.5;
    private final Random random = new Random();
    private GenSchedule initial;
    private ChartData fitnessAndIterationData = new ChartData("Genetic fitness");

    @Override
    public Schedule doCalculation(Semester semester, List<SubTask> subTasks) {
        createInitPopulation(semester, subTasks);
        if (initial == null) {
            initial = getBest();
        }

        for (int i = 0; i < maxIterations; i++) {
            iteration = i;
            double value = i == 0 ? initial.getOptValue() : getBest().getOptValue();
            fitnessAndIterationData.getData().add(new Number[]{iteration, value});
            System.out.printf("Curr iteration %d, best value = %f \n", i, value);

            List<GenSchedule> parents = chooseParents();
            //
            for (int j = 0; j < parents.size(); j += 2) {
                GenSchedule p1 = parents.get(j);
                GenSchedule p2 = parents.get((j + 1) % parents.size());
                population.add((GenSchedule) p1.doCrossover(p2, (int) (p2.getDaysWithTasks().size() * crossoverSize)));
            }
            population = selectNewGeneration();
            if (random.nextDouble() <= mutationProbability) {
                GenSchedule schedule = population.get(random.nextInt(population.size()));
                schedule.doMutation();
            }

        }
        return population.get(0);
    }

    @Override
    public double getInitialOptimizedValue() {
        return initial.getOptValue();
    }

    @Override
    public double getFinalOptimizedValue() {
        return getBest().getOptValue();
    }

    @Override
    public void setInitialSchedule(Schedule initialSchedule) {
        initial = new GenSchedule(initialSchedule);
    }


    private GenSchedule getBest() {
        Collections.sort(population, (o1, o2) -> (int) (o2.getFitness() - o1.getFitness()));
        return population.get(0);
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
        // sort ascending
        Collections.sort(population, (o1, o2) -> (int) (o1.getFitness() - o2.getFitness()));
        return population.subList(size / 2, size);
    }


    private void createInitPopulation(Semester semester, List<SubTask> subTasks) {
        population = new ArrayList<>(populationSize);
        int size;
        if (initial != null) {
            size = populationSize - 1;
            population.add(initial);
        } else {
            size = populationSize;
        }

        for (int i = 0; i < size; i++) {
            GenSchedule schedule = new GenSchedule(semester, subTasks);
            population.add(schedule);
        }

    }

    public ChartData getFitnessAndIterationData() {
        return fitnessAndIterationData;
    }

}
