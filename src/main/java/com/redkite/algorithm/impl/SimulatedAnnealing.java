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
    @Override
    public Schedule doCalculation(Semester semester, List<SubTask> subTasks) {
        return null;
    }

    private int fitnessFunction(Schedule schedule){
        return 0;
    }


}
