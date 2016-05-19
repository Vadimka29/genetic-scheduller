package com.redkite.algorithm.impl;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.AlgorithmType;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class AlgorithmFactory {
    private static Object algorithmRealization;

    public static Algorithm retrieveAlgorithmRealization(AlgorithmType algorithmType){
        switch (algorithmType){
            case GENETIC_ALGORITHM:
                algorithmRealization = new GeneticAlgorithm();
                break;
            case SIMANNEALING_ALGORITHM:
                algorithmRealization = new SimulatedAnnealingAlgorithm();
                break;
            case GREEDY_ALGORITHM:
                algorithmRealization = new GreedyAlgorithm();
                break;
        }
        
        return (Algorithm) Proxy.newProxyInstance(algorithmRealization.getClass().getClassLoader(),
                new Class[]{Algorithm.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result =  method.invoke(algorithmRealization, args);
                        return result;
                    }
                });
    }
}
