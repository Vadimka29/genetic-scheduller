package com.redkite.algorithm.geneticmodel;


public interface Chromosome<T> {
    T doCrossover(T chr, int segmentSize);
    void doMutation();
    double getFitness();
}
