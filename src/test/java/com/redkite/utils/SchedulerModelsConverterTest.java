package com.redkite.utils;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.redkite.algorithm.model.Semester;
import com.redkite.calendar.CalendarApi;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadym on 06.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class SchedulerModelsConverterTest {

    @Autowired
    private CalendarApi calendarApi;

    private static DateTime semesterStartDate;
    private static DateTime semesterEndDate;

    @BeforeClass
    public static void beforeClassSetup(){
        semesterStartDate = new DateTime("2016-05-01T00:00:00+02:00");
        semesterEndDate = new DateTime("2016-12-31T00:00:00+02:00");
    }

    @Test
    public void convertFromSubjectsModelToTask() throws Exception {

    }

    @Test
    public void convertGoogleEventsToSemester() throws Exception {
        List<Event> events = calendarApi.getEventsBetweenDates(semesterStartDate, semesterEndDate);
        Semester semester = SchedulerModelsConverter.convertGoogleEventsToSemester(events,
                Instant.ofEpochMilli(semesterStartDate.getValue()).atZone(ZoneId.systemDefault()).toLocalDate(),
                Instant.ofEpochMilli(semesterEndDate.getValue()).atZone(ZoneId.systemDefault()).toLocalDate());
        assertNotNull(events);
        assertTrue(!events.isEmpty());
        semester.getDays().forEach(day -> {
            assertNotNull(day);
            assertTrue(day.getFreeTime() >= 0);
        });
    }
}