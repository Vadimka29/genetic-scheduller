package com.redkite.calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadym on 03.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
public class CalendarApiTest {

    @Autowired
    private CalendarApi calendarApi;

    @Test
    public void testGetEventsBetweenDates() throws Exception {
        Instant now = Instant.now();
        Instant tomorrow = now.plus(1, ChronoUnit.DAYS);
        DateTime nowDateTime = new DateTime(now.toEpochMilli());
        DateTime tomorrowDateTime = new DateTime(tomorrow.toEpochMilli());
        List<Event> events = calendarApi.getEventsBetweenDates(nowDateTime, tomorrowDateTime);
        events.forEach(event -> {
            System.out.println(event);
            DateTime start = event.getStart().getDateTime();
            DateTime end = event.getEnd().getDateTime();
            assertTrue(start.getValue() >= now.toEpochMilli() && end.getValue() <= tomorrow.toEpochMilli());
        });
    }

    @Test
    public void testGetAllEvents() throws Exception {
        List<Event> allEvents = calendarApi.getAllEvents();
        Assert.notNull(allEvents);
        allEvents.forEach(System.out::println);
    }
}