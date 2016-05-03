package com.redkite.algorithm.geneticmodel;


public interface Chromosome<T> {
    T doCrossover(T chr);
    void doMutation();
    double getFitness();
}
