package com.redkite.algorithm.impl;


import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.AlgorithmType;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;
import com.redkite.calendar.CalendarApi;
import com.redkite.entities.Task;
import com.redkite.services.SubjectService;
import com.redkite.utils.SchedulerModelsConverter;
import com.redkite.xml.model.Subject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class GeneticAlgorithmTest {

    @Autowired
    private CalendarApi calendarApi;

    @Autowired
    private SubjectService subjectService;

    private static DateTime semesterStartDate;
    private static DateTime semesterEndDate;

    private static List<Task> workingTasks;

    private final int subjectsCount = 1;

    @BeforeClass
    public static void beforeClassSetup() {
        semesterStartDate = new DateTime("2016-09-01T00:00:00+02:00");
        semesterEndDate = new DateTime("2016-12-31T00:00:00+02:00");
//        semesterEndDate = new DateTime("2016-09-08T00:00:00+02:00");

    }

    @Before
    public void setUpData() {
        List<Subject> allSubjects = subjectService.getAllSubjects();
//        allSubjects =  allSubjects.subList(0, subjectsCount);
        workingTasks = SchedulerModelsConverter.convertFromSubjectsModelToTask(allSubjects);
    }

    @Test
    public void testDoCalculation() throws Exception {
        Algorithm geneticAlgorithm = AlgorithmFactory.retrieveAlgorithmRealization(AlgorithmType.GENETIC_ALGORITHM);
        List<Event> events = calendarApi.getEventsBetweenDates(semesterStartDate, semesterEndDate);

        Semester semester = SchedulerModelsConverter.convertGoogleEventsToSemester(events,
                Instant.ofEpochMilli(semesterStartDate.getValue()).atZone(ZoneId.systemDefault()).toLocalDate(),
                Instant.ofEpochMilli(semesterEndDate.getValue()).atZone(ZoneId.systemDefault()).toLocalDate());
        List<SubTask> subTasks = new ArrayList<>();
        workingTasks.forEach(task -> {
            subTasks.addAll(task.toSubTasks());
        });

        Schedule schedule = geneticAlgorithm.doCalculation(semester, subTasks);
        assertTrue(geneticAlgorithm.getFinalOptimizedValue() <= geneticAlgorithm.getInitialOptimizedValue());
    }
}
