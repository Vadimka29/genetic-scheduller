package com.redkite.controllers.rest;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.AlgorithmType;
import com.redkite.algorithm.impl.AlgorithmFactory;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;
import com.redkite.calendar.CalendarApi;
import com.redkite.entities.Task;
import com.redkite.entities.User;
import com.redkite.services.SubjectService;
import com.redkite.services.UserService;
import com.redkite.utils.SchedulerModelsConverter;
import com.redkite.xml.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 09.05.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class CalendarController {

    @Autowired
    private CalendarApi calendarApi;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    private static DateTime semesterStartDate = new DateTime("2016-09-01T00:00:00+02:00");
    private static DateTime semesterEndDate = new DateTime("2016-12-31T00:00:00+02:00");
    private List<Task> workingTasks;
    private List<Event> events;

    @PostConstruct
    private void setUpControllerData() throws IOException {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        workingTasks = SchedulerModelsConverter.convertFromSubjectsModelToTask(allSubjects);
        events = calendarApi.getEventsBetweenDates(semesterStartDate, semesterEndDate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test-schedule")
    public List<SubTask> getSchedule(ModelMap model){
        Algorithm simulatedAnnealing = AlgorithmFactory.retrieveAlgorithmRealization(AlgorithmType.SIMANNEALING_ALGORITHM);
        Semester semester = SchedulerModelsConverter.convertGoogleEventsToSemester(events,
                Instant.ofEpochMilli(semesterStartDate.getValue()).atZone(ZoneId.systemDefault()).toLocalDate(),
                Instant.ofEpochMilli(semesterEndDate.getValue()).atZone(ZoneId.systemDefault()).toLocalDate());
        List<SubTask> subTasks = new ArrayList<>();
//        List<Task> tasks = (List<Task>) model.get("tasks");
        workingTasks.forEach(task -> {
            subTasks.addAll(task.toSubTasks());
        });
        Schedule schedule = simulatedAnnealing.doCalculation(semester, subTasks);
        return schedule.getAllScheduledSubTasks();
    }


    public User getCurrUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        return userService.getUserByLogin(login);
    }
}
