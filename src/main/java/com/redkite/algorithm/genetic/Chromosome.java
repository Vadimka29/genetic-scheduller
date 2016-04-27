package com.redkite.algorithm.genetic;


public interface Chromosome<T> {
    T doCrossover(T chr);
    void doMutation();
    double getFitness();
}
