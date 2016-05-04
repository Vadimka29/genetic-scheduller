package com.redkite.algorithm.impl;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;

import java.util.List;

/**
 * Created by Vadym on 24.04.2016.
 */
class SimulatedAnnealing implements Algorithm {
    private final double INITIAL_TEMPERATURE = 10;
    private final double TEMPERATURE_INCREASING_PERCENT = 0.97;

    @Override
    public Schedule doCalculation(Semester semester, List<SubTask> subTasks) {
        //get initial schedule
        //calculate it's energy
        //generate new shedule
        //calculate it's newEnergy
        //if acceptanceProbability > Math.random -> accept new schedule
        //cool temperature
        return null;
    }

    private int energyFunction(Schedule schedule){
        return 0;
    }


    private double calculateAcceptanceProbability(int energy, int newEnergy, double temperature){
        //if the new solution is better, accept it
        if(newEnergy <= energy){
            return 1;
        }
        //if the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy)/temperature);
    }
    private double coolSystemTemperature(double currentTemperature){
        return currentTemperature*TEMPERATURE_INCREASING_PERCENT;
    }


}
