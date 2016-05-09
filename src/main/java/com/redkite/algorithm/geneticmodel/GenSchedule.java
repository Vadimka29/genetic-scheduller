package com.redkite.algorithm.geneticmodel;

import com.redkite.algorithm.model.Day;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenSchedule extends Schedule implements Chromosome<Schedule> {
    private double crossoverSize = 0.3;
    private double crossoverProbability = 0.5;
    private final Random random = new Random();

    public GenSchedule(Semester semester, List<SubTask> subTasks) {
        super(semester, subTasks);
    }

    @Override
    public Schedule doCrossover(Schedule chr) {
        ImmutablePair<List, List> pair = convertScheduleToDayList(this, chr);
        List list1 = pair.getLeft();
        List list2 = pair.getRight();
        int start = random.nextInt(list1.size());
        int end = (int) (start + Math.round(list1.size()*crossoverSize));
//        start =

        return null;
    }

    @Override
    public void doMutation() {

    }


    private Schedule convertSubTasksToSchedule (List<ImmutablePair<Day, SubTask>> scheduleList) {
//        scheduleList.stream()
//                .filter()
//                .collect(Collectors.groupingBy(SubTask::getDate))
//                .entrySet()
//                .stream()
//                .map(entry -> new Day());
        return null;
    }

    private ImmutablePair<List, List> convertScheduleToDayList(Schedule s1, Schedule s2) {
        List<Day> days1 = s1.getDays();
        List<Day> days2 = s2.getDays();
        List<ImmutablePair<Day, SubTask>> scheduleList1 = new ArrayList<>();
        List<ImmutablePair<Day, SubTask>> scheduleList2 = new ArrayList<>();

        for (int i = 0; i < s1.getNumberOfDays(); i++) {
            List<SubTask> tasks1 = days1.get(i).getSubTasks();
            List<SubTask> tasks2 = days2.get(i).getSubTasks();
            int size = Math.max(tasks1.size(), tasks2.size());
            for (int j = 0; j < size; j++) {
                SubTask subTask1 = null;
                SubTask subTask2 = null;

                if(tasks1.size() > j) {
                    subTask1 = tasks1.get(j);
                }

                if(tasks2.size() > j) {
                    subTask2 = tasks2.get(j);
                }

                scheduleList1.add(new ImmutablePair<>(days1.get(i), subTask1));
                scheduleList2.add(new ImmutablePair<>(days2.get(i), subTask2));

            }
        }

        return new ImmutablePair<>(scheduleList1, scheduleList2);
    }

    @Override
    public double getFitness() {
        List<Day> days = semester.getDays();
        double averageWorkLoad = days.stream().mapToDouble(Day::getDayWorkLoad).sum() / days.size();
        int sumDeadLines = (int) tasks.stream().mapToLong(task -> {
            LocalDate last = findLastTaskInGroup(task.getTaskName()).getExecutionDate();
            return ChronoUnit.DAYS.between(semester.getStart(), last) * (25 - task.getPriority());
        }).sum();
        return sumDeadLines * averageWorkLoad;
    }


}
