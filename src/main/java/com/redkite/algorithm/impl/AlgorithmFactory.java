package com.redkite.algorithm.impl;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.AlgorithmType;
import com.redkite.algorithm.ChartDataSuit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class AlgorithmFactory {

    public static Algorithm retrieveAlgorithmRealization(AlgorithmType algorithmType) {
        final Object algorithmRealization;
        switch (algorithmType) {
            case GENETIC_ALGORITHM:
                algorithmRealization = new GeneticAlgorithm();
                break;
            case SIMANNEALING_ALGORITHM:
                algorithmRealization = new SimulatedAnnealingAlgorithm();
                break;
            case GREEDY_ALGORITHM:
                algorithmRealization = new GreedyAlgorithm();
                break;
            default:
                algorithmRealization = new GeneticAlgorithm();
        }

        return (Algorithm) Proxy.newProxyInstance(algorithmRealization.getClass().getClassLoader(),
                new Class[]{Algorithm.class, ChartDataSuit.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = method.invoke(algorithmRealization, args);
                        return result;
                    }
                });
    }
}
