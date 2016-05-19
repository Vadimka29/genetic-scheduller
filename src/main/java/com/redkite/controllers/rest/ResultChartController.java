package com.redkite.controllers.rest;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.redkite.algorithm.Algorithm;
import com.redkite.algorithm.AlgorithmType;
import com.redkite.algorithm.ChartDataSuit;
import com.redkite.algorithm.impl.AlgorithmFactory;
import com.redkite.algorithm.model.Schedule;
import com.redkite.algorithm.model.Semester;
import com.redkite.algorithm.model.SubTask;
import com.redkite.calendar.CalendarApi;
import com.redkite.entities.Task;
import com.redkite.entities.chart.ChartObject;
import com.redkite.entities.chart.ChartObjectBuilder;
import com.redkite.entities.chart.ChartsHolder;
import com.redkite.services.SubjectService;
import com.redkite.utils.SchedulerModelsConverter;
import com.redkite.xml.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 17.05.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class ResultChartController {
    @Autowired
    private CalendarApi calendarApi;
    @Autowired
    private SubjectService subjectService;
    private static List<Task> workingTasks;
    private static List<SubTask> subTasks;
    private static Semester semester;

    private static DateTime semesterStartDate = new DateTime("2016-09-01T00:00:00+02:00");
    private static DateTime semesterEndDate = new DateTime("2016-12-31T00:00:00+02:00");
    private final int subjectsCount = 1;

    private void prepareData() throws IOException {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        workingTasks = SchedulerModelsConverter.convertFromSubjectsModelToTask(allSubjects);
        List<Event> events = calendarApi.getEventsBetweenDates(semesterStartDate, semesterEndDate);
        semester = SchedulerModelsConverter.convertGoogleEventsToSemester(events,
                Instant.ofEpochMilli(semesterStartDate.getValue()).atZone(ZoneId.systemDefault()).toLocalDate(),
                Instant.ofEpochMilli(semesterEndDate.getValue()).atZone(ZoneId.systemDefault()).toLocalDate());
        subTasks = new ArrayList<>();
        workingTasks.forEach(task -> {
            subTasks.addAll(task.toSubTasks());
        });
    }
    @RequestMapping(value = "/stub/chart", method = RequestMethod.GET)
    public List<ChartObject> getChartInfo() throws IOException {
        prepareData();

        Algorithm simulatedAnnealingAlgorithm = AlgorithmFactory.retrieveAlgorithmRealization(AlgorithmType.SIMANNEALING_ALGORITHM);
        Algorithm greedyAlgorithm = AlgorithmFactory.retrieveAlgorithmRealization(AlgorithmType.GREEDY_ALGORITHM);

        Schedule simulatedAnnealingSchedule = simulatedAnnealingAlgorithm.doCalculation(semester, subTasks);
        Schedule greedyAlgorithmSchedule = greedyAlgorithm.doCalculation(semester, subTasks);

        ChartObjectBuilder temperatureAndIterationChartBuilder = new ChartObjectBuilder();
        ChartObjectBuilder energyAndIterationChartBuilder = new ChartObjectBuilder();
        ChartObjectBuilder probabilityAndIterChartBuilder = new ChartObjectBuilder();
        ChartObjectBuilder probabilityAndTempeChartBuilder = new ChartObjectBuilder();

        //build temperature graphic
        temperatureAndIterationChartBuilder
                .initialize("TemperatureChart", "Iteration", "Temperature")
                .with(((ChartDataSuit) simulatedAnnealingAlgorithm).getTemperatureAndIterData())
                .with(((ChartDataSuit) greedyAlgorithm).getTemperatureAndIterData())
                .build();

        //build energy graphic
        energyAndIterationChartBuilder
                .initialize("EnergyChart", "Iteration", "Energy")
                .with(((ChartDataSuit) simulatedAnnealingAlgorithm).getEnergyFuncAndIterData())
                .with(((ChartDataSuit) greedyAlgorithm).getEnergyFuncAndIterData())
                .build();

        probabilityAndIterChartBuilder
                .initialize("ProbabilityChart", "Probability", "Iteration")
                .with(((ChartDataSuit) simulatedAnnealingAlgorithm).getProbabilityAndIterData())
                .build();

        probabilityAndTempeChartBuilder
                .initialize("ProbabilityTemperChart", "Probability", "Temperature")
                .with(((ChartDataSuit) simulatedAnnealingAlgorithm).getProbabilityAndTemperData())
                .build();

        return ChartsHolder.getChartObjectList();
    }
}
