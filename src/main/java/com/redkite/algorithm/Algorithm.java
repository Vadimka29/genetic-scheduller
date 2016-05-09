package com.redkite.algorithm;


import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;

import java.util.List;

public interface Algorithm {
    Schedule doCalculation(Semester semester, List<SubTask> subTasks);

    /**
     * @return initial value of estimated function (genetic function/energy function)
     */
    double getInitialOptimizedValue();

    /**
     * @return optimized value of estimated function(genetic function/energy function)
     */
    double getFinalOptimizedValue();
}
