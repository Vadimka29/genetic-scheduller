package com.redkite.algorithm.model;


import com.google.api.client.util.Preconditions;
import com.redkite.entities.Task;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Schedule implements Serializable{
    //optimization
    private Set<Day> daysWithTasks;
    protected Set<Task> tasks;
    private int numberOfTasks;
    private final Random random = new Random();
    protected final Semester semester;

    //TODO maybe better pass Tasks
    public Schedule(Semester semester, List<SubTask> subTasks) {
        this.semester = SerializationUtils.clone(semester);
        createScheduleForSemester(subTasks);
    }

    public Schedule(List<Day> daysWithTasks) {
        this.semester = new Semester(daysWithTasks);
        for (Day daysWithTask : daysWithTasks) {
            for (SubTask subTask : daysWithTask.getSubTasks()) {
                tasks.add(subTask.getParentTask());
            }
        }
    }
    /**
     * Fill semester with subtask in random positions and remember all unique task
     * */
    private void createScheduleForSemester(List<SubTask> subTasks) {
        List<Day> days = semester.getDays();
        tasks = new HashSet<>();
        daysWithTasks = new HashSet<>();
        numberOfTasks = subTasks.size();
        for (SubTask subTask : subTasks) {
            tasks.add(subTask.getParentTask());
            //need to respect the two hard stop criterias: 1) Don't schedule the subtask before retrieve date
            //don't schedule the subtasks after the deadlines
            int subtaskRetrieveDayIndex = getIndexOfDay(subTask.getParentTask().getCreatedDate());
            int subtaskDeadLineDayIndex = getIndexOfDay(subTask.getParentTask().getDeadline());
            boolean isSubTaskAdded = false;
            while(!isSubTaskAdded) {
                int index = random.nextInt(subtaskDeadLineDayIndex + 1 - subtaskRetrieveDayIndex) + subtaskRetrieveDayIndex;
                Day day = days.get(index);
                if(day.getLeftFreeTimeForDay() - subTask.getDuration() >= 0) {
                    daysWithTasks.add(day);
                    day.addTask(subTask);
                    isSubTaskAdded = true;
                }
            }
        }
    }

    public int getIndexOfDay(LocalDate localDate){
        for(int i = 0; i <  semester.getDays().size(); i++){
            if(localDate.equals( semester.getDays().get(i).getDate())){
                return i;
            }
        }
        return -1;
    }

    public List<Day> getDays() {
        return new ArrayList<>(semester.getDays());
    }


    public List<SubTask> getAllScheduledSubTasks(){
        List<SubTask> allScheduleSubTasks = new ArrayList<>();
        semester.getDays().forEach(day -> {
            allScheduleSubTasks.addAll(day.getSubTasks());
        });
        return allScheduleSubTasks;
    }


    public SubTask findLastTaskInGroup(String taskName) {
        Preconditions.checkNotNull(taskName, "taskName can't be null");
        SubTask last = null;
        for (Day day : semester.getDays()) {
            for (SubTask subTask : day.getSubTasks()) {
                if (subTask.getParentName().equals(taskName)) {
                    if (last == null)
                        last = subTask;
                    if (subTask.getExecutionDate().isAfter(last.getExecutionDate()))
                        last = subTask;

                }
            }
        }
        return last;
    }


    public LocalDate getStart() {
        return semester.getStart();
    }

    public LocalDate getEnd() {
        return semester.getEnd();
    }

    public Semester getSemester() {
        return semester;
    }

    public int getNumberOfDays() {
        return  semester.getNumberOfDays();
    }

    public Set<Day> getDaysWithTasks() {
        return daysWithTasks;
    }


    @Override
    public String toString() {
        return StringUtils.join(semester.getDays().stream()
                .map(Day::toString)
                .collect(Collectors.toList()), "\n");
    }
}
