package com.redkite.utils;


import com.google.common.base.Preconditions;
import com.redkite.algorithm.model.Day;
import com.redkite.algorithm.model.SubTask;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskUtils {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static SubTask findFirstTaskInGroup(SubTask subTaskSample, List<Day> days) {
        Preconditions.checkNotNull(subTaskSample, "subTaskSample can't be null");
        Preconditions.checkNotNull(days, "days list can't be null!");

        SubTask first = null;
        for (Day day : days) {
            for (SubTask subTask : day.getSubTasks()) {
                if (subTask.getParentName().equals(subTaskSample.getParentName())) {
                    if (first == null)
                        first = subTask;
                    if (subTask.getExecutionDate().isBefore(first.getExecutionDate()))
                        first = subTask;
                }
            }
        }
        return first;
    }

    @Deprecated
    public static SubTask findLastTaskInGroup(SubTask subTaskSample, List<Day> days) {
        Preconditions.checkNotNull(subTaskSample, "subTaskSample can't be null");
        Preconditions.checkNotNull(days, "days list can't be null!");

        SubTask last = null;
        for (Day day : days) {
            for (SubTask subTask : day.getSubTasks()) {
                if (subTask.getParentName().equals(subTaskSample.getParentName())) {
                    if (last == null)
                        last = subTask;
                    if (subTask.getExecutionDate().isAfter(last.getExecutionDate()))
                        last = subTask;

                }
            }
        }
        return last;
    }

    public static DateTimeFormatter getDateTimeFormatter(){
        return dtf;
    }
}
