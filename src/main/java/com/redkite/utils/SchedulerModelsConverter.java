package com.redkite.utils;

import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.api.services.calendar.model.Event;
import com.redkite.algorithm.model.Semester;
import com.redkite.entities.Task;
import com.redkite.xml.model.Priority;
import com.redkite.xml.model.Subject;
import com.redkite.xml.model.SubjectItem;
import com.redkite.xml.model.SubjectsHolder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 03.05.2016.
 */
public class SchedulerModelsConverter {

    public static List<Task> convertFromSubjectsModelToTask(SubjectsHolder subjectsHolder) {
        Assert.notNull(subjectsHolder, "subjectHolder can't be null");

        List<Task> resultList = new ArrayList<>();
        for (Subject subject : subjectsHolder.getSubject()) {
            for (SubjectItem subjectItem : subject.getSubjectItem()) {
                Task task = new Task();
                task.setTaskName(subjectItem.getWorkName());
                LocalDate retrieveDate = subjectItem.getRetrieveDate().toGregorianCalendar().toZonedDateTime().toLocalDate();
                LocalDate deadLine = subjectItem.getDeadline().toGregorianCalendar().toZonedDateTime().toLocalDate();
                task.setCreatedDate(retrieveDate);
                task.setDeadline(deadLine);
                task.setDuration(subjectItem.getDuration());
                task.setHoursPerDay(subjectItem.getHoursPerDay());

                //TODO: think how to setup priority in normal way
                int subjectItemPriority = calculateSubjectItemPriority(subject, subjectItem);
                task.setPriority(subjectItemPriority);
                resultList.add(task);
            }
        }
        return resultList;
    }

    private static int calculateSubjectItemPriority(Subject subject, SubjectItem subjectItem){
        int subjectUserPriority = 1;
        int subjectCompletionTypePriority = 1;
        switch (subject.getPriority()) {
            case LOW:
                subjectUserPriority = 1;
                break;
            case MEDIUM:
                subjectUserPriority = 3;
                break;
            case HIGH:
                subjectUserPriority = 5;
                break;
        }
        switch (subject.getCompletionType()) {
            case CREDIT:
                subjectCompletionTypePriority = 1;
                break;
            case DIFFERENTIATED_CREDIT:
                subjectCompletionTypePriority = 3;
                break;
            case EXAM:
                subjectCompletionTypePriority = 5;
                break;
        }
        return subjectUserPriority * subjectCompletionTypePriority;
    }

    //TODO: понять кто сформирует первоначальный обьект Semester
    public static Semester convertGoogleEventsToSemester(List<Event> googleEvents, LocalDate startDate, LocalDate endDate){
        Semester semester = new Semester(startDate, endDate);
        return null;
    }
}