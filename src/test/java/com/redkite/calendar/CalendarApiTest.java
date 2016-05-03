package com.redkite.calendar;

import com.google.api.services.calendar.model.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vadym on 03.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistence.xml"})
public class CalendarApiTest {

    @Autowired
    private CalendarApi calendarApi;

    @Test
    public void getEvents() throws Exception {

    }

    @Test
    public void getAllEvents() throws Exception {
        List<Event> allEvents = calendarApi.getAllEvents();
        Assert.notNull(allEvents);
        allEvents.forEach(System.out::println);
    }
}