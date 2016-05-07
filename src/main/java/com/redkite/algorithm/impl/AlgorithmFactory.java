package com.redkite.algorithm.impl;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.AlgorithmType;
import com.redkite.algorithm.impl.GeneticAlgorithm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Vadym on 26.04.2016.
 */
public class AlgorithmFactory {
    private static Object algorithmRealization;

    public static Algorithm retrieveAlgorithmRealization(AlgorithmType algorithmType){
        switch (algorithmType){
            case GENETIC_ALGORITHM:
                algorithmRealization = new GeneticAlgorithm();
                break;
            case SIMANNEALING_ALGORITHM:
                //TODO:add algorithm
                break;
        }
        
        return (Algorithm) Proxy.newProxyInstance(algorithmRealization.getClass().getClassLoader(),
                new Class[]{Algorithm.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Start performance measure");
                        Object result =  method.invoke(algorithmRealization, args);
                        System.out.println("End performance measure");
                        return result;
                    }
                });
    }
}
