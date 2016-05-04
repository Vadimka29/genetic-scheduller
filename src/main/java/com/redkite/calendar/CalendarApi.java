package com.redkite.calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.util.List;

/**
 * Created by scread on 12.04.16.
 */
public interface CalendarApi {
    void addEvent(String name, DateTime startDate, int duration) throws IOException;

    void addEvent(String name, DateTime startDate, DateTime endDate) throws IOException;

    void addEvent(Event event);

    List<Event> getAllEvents() throws IOException;

    List<Event> getNFirstEvents(int amount) throws IOException;

    List<Event> getEventsBetweenDates(DateTime startDate, DateTime endDate) throws IOException;

    Event getEvent(String eventId) throws IOException;

    void deleteEvent(Event event) throws IOException;

    void deleteEvent(String eventId) throws IOException;

    void editEvent(Event event) throws IOException;

}
