package com.redkite.utils;


import com.google.common.base.Preconditions;
import com.redkite.algorithm.model.Day;
import com.redkite.algorithm.model.SubTask;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TaskUtils {
    public static SubTask findFirstTaskInGroup(SubTask subTaskSample, List<Day> days) {
        Preconditions.checkNotNull(subTaskSample, "subTaskSample can't be null");
        Preconditions.checkNotNull(days, "days list can't be null!");

        SubTask first = null;
        for (Day day : days) {
            for (SubTask subTask : day.getSubTasks()) {
                if (subTask.getParentName().equals(subTaskSample.getParentName())) {
                    if (first == null)
                        first = subTask;
                    if (subTask.getDate().isBefore(first.getDate()))
                        first = subTask;
                }
            }
        }
        return first;
    }

    private SubTask findLastTaskInGroup(SubTask subTaskSample, List<Day> days) {
        Preconditions.checkNotNull(subTaskSample, "subTaskSample can't be null");
        Preconditions.checkNotNull(days, "days list can't be null!");

        SubTask last = null;
        for (Day day : days) {
            for (SubTask subTask : day.getSubTasks()) {
                if (subTask.getParentName().equals(subTaskSample.getParentName())) {
                    if (last == null)
                        last = subTask;
                    if (subTask.getDate().isAfter(last.getDate()))
                        last = subTask;

                }
            }
        }
        return last;
    }
}
