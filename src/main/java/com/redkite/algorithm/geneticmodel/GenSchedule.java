package com.redkite.algorithm.geneticmodel;

import com.redkite.algorithm.model.Day;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;
import com.rits.cloning.Cloner;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GenSchedule extends Schedule implements Chromosome<Schedule> {
    private double crossoverSize = 0.3;
    private double mutationNumber = 0.2;
    private final int FAILED_ATTEMPTS_COUNT = 300;
    private final Random random = new Random();


    public GenSchedule(Schedule sch) {
        super(sch.getSemester(), sch.getAllScheduledSubTasks());
    }

    public GenSchedule(Semester semester, List<SubTask> subTasks) {
        super(semester, subTasks);
    }

    public GenSchedule(ArrayList<Day> list) {
        super(list);
    }

    //PMX crossover
    @Override
    public Schedule doCrossover(Schedule chr, int segmentSize) {
//        ImmutablePair<List<ImmutablePair<Day, SubTask>>, List<ImmutablePair<Day, SubTask>>> pair = convertScheduleToDayList(chr);
//        List<ImmutablePair<Day, SubTask>> parent1 = pair.getLeft();
//        List<ImmutablePair<Day, SubTask>> parent2 = pair.getRight();
//        List<ImmutablePair<Day, SubTask>> offspring = new ArrayList<>(parent1.size());
//        int start = random.nextInt((int) (parent1.size() * (1 - crossoverSize)));
//        int end = (int) (start + parent1.size() * crossoverSize);
//        List<ImmutablePair<Day, SubTask>> segment1 = parent1.subList(start, end);
//        List<ImmutablePair<Day, SubTask>> segment2 = parent2.subList(start, end);
//        offspring.addAll(start, segment1);
//
//        for (int i = 0; i < segment2.size(); i++) {
//            if(true) {
//                ImmutablePair<Day, SubTask> day1 = segment1.get(i);
//                ImmutablePair<Day, SubTask> day2 = segment2.get(i);
//            }
//
//        }
//
//        return convertDayListToSchedule(offspring);

        return crossover(this, segmentSize);
    }


    private Schedule crossover(Schedule sch, int segmentSize) {
        Schedule offspring = SerializationUtils.clone(sch);
        for (int i = 0; i < segmentSize; i++) {
            doRandomSwap(offspring);
        }
        return offspring;
    }


    private void doRandomSwap(Schedule sch) {
        //days are already sorted
        List<Day> days = sch.getDays();
        List<Day> daysWithTasks = new ArrayList<>(sch.getDaysWithTasks());
        int daysCount = sch.getDays().size();
        int daysWithTasksCount = sch.getDaysWithTasks().size();
        //get fromDay (should be a day with the tasks)

        int swapFromDayIndex = random.nextInt(daysWithTasksCount);
        Day fromDay = daysWithTasks.get(swapFromDayIndex);

        //retrieve subtask index which should be moved to another day
        int swappedSubTaskIndex = random.nextInt(fromDay.getSubTasks().size());
        SubTask movedSubtask = fromDay.getSubTasks().get(swappedSubTaskIndex);

        boolean subtaskInsertedSuccessfully = false;
        int attemp_counter = 0;
        while (!subtaskInsertedSuccessfully && attemp_counter <= FAILED_ATTEMPTS_COUNT) {
            //hard stop criteria, subtask can't be scheduled before retrieve date
            int subTaskRetrieveDayIndex = sch.getIndexOfDay(movedSubtask.getParentTask().getCreatedDate());
            //hard stop criteria, subtask can't be scheduled after deadline date
            int subTaskDeadLineDayIndex = sch.getIndexOfDay(movedSubtask.getParentTask().getDeadline());
            int swapToDayIndex = random.nextInt(subTaskDeadLineDayIndex + 1 - subTaskRetrieveDayIndex) + subTaskRetrieveDayIndex;
            Day toDay = days.get(swapToDayIndex);
            //hard stop criteria(we can't exceed 12 hours per day)
            if (toDay.getLeftFreeTimeForDay() - movedSubtask.getDuration() >= 0) {
                //if toDay had no subtask - add this day to daysWithTasks
                if (toDay.getSubTasks().isEmpty()) {
                    sch.getDaysWithTasks().add(toDay);
                }
                toDay.randomlyMoveSubTask(movedSubtask, fromDay);
                //remove day from daysWithSubtask if there is no subtask here now
                if (fromDay.getSubTasks().isEmpty()) {
                    sch.getDaysWithTasks().remove(fromDay);
                }
                subtaskInsertedSuccessfully = true;
            }
            attemp_counter++;
        }
    }

    //swap mutation
    @Override
    public void doMutation() {
        doRandomSwap(this);
    }


    private GenSchedule convertDayListToSchedule(List<ImmutablePair<Day, SubTask>> scheduleList) {
        Map<LocalDate, Day> days = new HashMap<>();
        for (ImmutablePair<Day, SubTask> pair : scheduleList) {
            Day curr = pair.getLeft();
            if (days.containsKey(curr.getDate())) {
                days.get(curr.getDate()).addTask(pair.getRight());
            } else {
                curr.freeDay();
                days.put(curr.getDate(), curr);
            }
        }
        ArrayList<Day> list = new ArrayList<>(days.values());
        Collections.sort(list, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return new GenSchedule(list);
    }

    private ImmutablePair<List<ImmutablePair<Day, SubTask>>, List<ImmutablePair<Day, SubTask>>> convertScheduleToDayList(Schedule s2) {
        Cloner cloner = new Cloner();
        // small work around)
        List<Day> days1 = cloner.deepClone(semester.getDays());
        List<Day> days2 = cloner.deepClone(s2.getDays());
        List<ImmutablePair<Day, SubTask>> scheduleList1 = new ArrayList<>();
        List<ImmutablePair<Day, SubTask>> scheduleList2 = new ArrayList<>();

        for (int i = 0; i < semester.getNumberOfDays(); i++) {
            List<SubTask> tasks1 = days1.get(i).getSubTasks();
            List<SubTask> tasks2 = days2.get(i).getSubTasks();
            int size = Math.max(tasks1.size(), tasks2.size());
            for (int j = 0; j < size; j++) {
                SubTask subTask1 = null;
                SubTask subTask2 = null;

                if (tasks1.size() > j) {
                    subTask1 = tasks1.get(j);
                }

                if (tasks2.size() > j) {
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

        return getWorth() - (sumDeadLines * averageWorkLoad);
    }

    // the worth case when all task placed on last day
    // this abstraction because that violate hard catteries
    // but it allow us adjust our fitness function for GA and minimize one
    public double getWorth() {
        return getNumberOfDays() * tasks.size() * 25;
    }


    public double getOptValue() {
        return getWorth() - getFitness();
    }
}
