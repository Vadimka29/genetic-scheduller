package com.redkite.calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    public void testGetEvents() throws Exception {
        DateTime now = new DateTime(new Date(), TimeZone.getDefault());
//        calendarApi.getEvents(now, now.);
    }

    @Test
    public void testGetAllEvents() throws Exception {
        List<Event> allEvents = calendarApi.getAllEvents();
        Assert.notNull(allEvents);
        allEvents.forEach(System.out::println);
    }
}