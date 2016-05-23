package com.redkite.algorithm.impl;

import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.ChartDataSuit;
import com.redkite.algorithm.model.Day;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;
import com.redkite.entities.chart.ChartData;
import com.redkite.utils.TaskUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.time.temporal.ChronoUnit;
import java.util.*;


class SimulatedAnnealingAlgorithm implements Algorithm, ChartDataSuit {
    private final double INITIAL_TEMPERATURE = 10_000;
    private final double TEMPERATURE_INCREASING_PERCENT = 0.9;
    private final int FAILED_ATTEMPTS_COUNT = 300;

    private final Random random = new Random();
    private double initialScheduleEnergy;
    private double bestScheduleEnergy;
    private Schedule initialSchedule;

    //chartsData
    private ChartData temperatureAndIterData = new ChartData("SimAnnealing Temperature");
    private ChartData energyFuncAndIterData = new ChartData("SimAnnealing energy");
    private ChartData probabilityAndIterData = new ChartData("SimAnnealing Probabability Iter");
    private ChartData probabilityAndTemperData = new ChartData("SimAnnealing Probability Temp");

    @Override
    public Schedule doCalculation(Semester semester, List<SubTask> subTasks) {
        //generate initial schedule
        if(initialSchedule == null) {
            initialSchedule = new Schedule(semester, subTasks);
        }
        initialScheduleEnergy = calculateEnergy(initialSchedule);
//        System.out.println(initialSchedule);

        //best schedule is initial one
        Schedule currentBestSchedule = initialSchedule;
        double currentBestScheduleEnergy = initialScheduleEnergy;

        double temperature = INITIAL_TEMPERATURE;
        int iterationNumber = 0;
        while (temperature > 1 && iterationNumber <= 15000) {
            temperatureAndIterData.getData().add(new Double[]{(double) iterationNumber, temperature});
            //generate new shedule
            Schedule newGeneratedSchedule = generateNewSchedule(currentBestSchedule);
            //calculate it's newEnergy
            double energyOfNewSchedule = calculateEnergy(newGeneratedSchedule);
            //calculate new schedule acceptence probability (this value is smaller if temperature is smaller)
            double acceptanceProbability = calculateAcceptanceProbability(currentBestScheduleEnergy, energyOfNewSchedule, temperature);

            probabilityAndIterData.getData().add(new Double[]{(double) iterationNumber, acceptanceProbability});
            probabilityAndTemperData.getData().add(new Double[]{(double) temperature, acceptanceProbability});

            double generatedProbability = random.nextDouble();
            //if acceptanceProbability > Math.random -> accept new schedule
            if (acceptanceProbability > generatedProbability) {
                currentBestSchedule = newGeneratedSchedule;
                currentBestScheduleEnergy = energyOfNewSchedule;
            }
            energyFuncAndIterData.getData().add(new Double[]{(double) iterationNumber, currentBestScheduleEnergy});

//            System.out.println("temperature: " + temperature);
//            System.out.println("currest best schedule enery: " + currentBestScheduleEnergy);
//            System.out.println("current energy: " + energyOfNewSchedule);
//            System.out.println("\n\n");

            //cool temperature
            if(iterationNumber >= 2000) {
                temperature = coolSystemTemperature(temperature, iterationNumber);
            }
            iterationNumber++;
        }
        bestScheduleEnergy = currentBestScheduleEnergy;
//        System.out.println(currentBestSchedule);
        return currentBestSchedule;
    }


    private Schedule generateNewSchedule(Schedule currentSchedule) {
        Schedule generatedSchedule = SerializationUtils.clone(currentSchedule);
        //days are already sorted
        List<Day> days = generatedSchedule.getDays();
        List<Day> daysWithTasks = new ArrayList<>(generatedSchedule.getDaysWithTasks());
        int daysCount = generatedSchedule.getDays().size();
        int daysWithTasksCount = generatedSchedule.getDaysWithTasks().size();

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
            int subTaskRetrieveDayIndex = generatedSchedule.getIndexOfDay(movedSubtask.getParentTask().getCreatedDate());
            //hard stop criteria, subtask can't be scheduled after deadline date
            int subTaskDeadLineDayIndex = generatedSchedule.getIndexOfDay(movedSubtask.getParentTask().getDeadline());
            int swapToDayIndex = random.nextInt(subTaskDeadLineDayIndex + 1 - subTaskRetrieveDayIndex) + subTaskRetrieveDayIndex;
            Day toDay = days.get(swapToDayIndex);
            //hard stop criteria(we can't exceed 12 hours per day)
            if (toDay.getLeftFreeTimeForDay() - movedSubtask.getDuration() >= 0) {
                //if toDay had no subtask - add this day to daysWithTasks
                if (toDay.getSubTasks().isEmpty()) {
                    generatedSchedule.getDaysWithTasks().add(toDay);
                }
                toDay.randomlyMoveSubTask(movedSubtask, fromDay);
                //remove day from daysWithSubtask if there is no subtask here now
                if (fromDay.getSubTasks().isEmpty()) {
                    generatedSchedule.getDaysWithTasks().remove(fromDay);
                }
                subtaskInsertedSuccessfully = true;
            }
            attemp_counter++;
        }
        return generatedSchedule;
    }

    private double calculateEnergy(Schedule schedule) {
        List<SubTask> lastSubtasks = getJustLastSubtaskForEveryTask(schedule.getAllScheduledSubTasks(), schedule.getDays());
        int finishDaysSummWithPriority = 0;
        if (!lastSubtasks.isEmpty()) {
            finishDaysSummWithPriority = (int) ChronoUnit.DAYS.between(schedule.getStart(), lastSubtasks.get(0).getExecutionDate());
            //iterate just through the last subtask of every task
            for (SubTask subTask : lastSubtasks) {
                finishDaysSummWithPriority += ChronoUnit.DAYS.between(schedule.getStart(), subTask.getExecutionDate())
                        * (25 - subTask.getParentTask().getPriority());
            }
        }
        double dayWorkLoadSumm = 0;
        for (Day day : schedule.getDays()) {
            dayWorkLoadSumm += day.getDayWorkLoad();
        }
        return finishDaysSummWithPriority * (dayWorkLoadSumm / schedule.getDays().size());
    }

    private List<SubTask> getJustLastSubtaskForEveryTask(List<SubTask> subTasks, List<Day> days) {
        Set<SubTask> lastSubtasks = new HashSet<>();
        for (SubTask subTask : subTasks) {
            lastSubtasks.add(TaskUtils.findLastTaskInGroup(subTask, days));
        }
        return new ArrayList<>(lastSubtasks);
    }

    private double calculateAcceptanceProbability(double currentEnergy, double newEnergy, double temperature) {
        //if the new solution is better, accept it
        if (newEnergy <= currentEnergy) {
            return 1;
        }
        //if the new solution is worse, calculate an acceptance probability
        return Math.exp(-((newEnergy - currentEnergy) / temperature));
    }

    private double coolSystemTemperature(double currentTemperature, int iterationNumber) {
        return INITIAL_TEMPERATURE * Math.pow(TEMPERATURE_INCREASING_PERCENT, Math.pow(iterationNumber - 2000, 0.5));
    }

    @Override
    public double getFinalOptimizedValue() {
        return bestScheduleEnergy;
    }

    @Override
    public double getInitialOptimizedValue() {
        return initialScheduleEnergy;
    }

    public ChartData getTemperatureAndIterData() {
        return temperatureAndIterData;
    }

    public ChartData getEnergyFuncAndIterData() {
        return energyFuncAndIterData;
    }

    public ChartData getProbabilityAndIterData() {
        return probabilityAndIterData;
    }

    public ChartData getProbabilityAndTemperData() {
        return probabilityAndTemperData;
    }

    public void setInitialSchedule(Schedule initialSchedule) {
        this.initialSchedule = initialSchedule;
    }
}
