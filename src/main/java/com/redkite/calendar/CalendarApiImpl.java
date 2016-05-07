package com.redkite.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import java.io.*;
import java.util.*;

/**
 * Created by scread on 12.04.16.
 */
public class CalendarApiImpl implements CalendarApi {
    private com.google.api.services.calendar.Calendar service = getCalendarService();

    /**
     * Calendar identifier and can either be the email address of the calendar on which to create
     * the event or a special keyword 'primary' which will use the primary calendar of the logged in user.
     */
    public static final String CALENDAR_ID = "primary";

    /**
     * Time zone for events.
     */
    public static final String TIME_ZONE = "Europe/Kiev";


    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Genetic Scheduler Web Application";

    /**
     * Directory to store user credentials for this application.
     */
    private static final File DATA_STORE_DIR = new File(System.getProperty("user.home"), ".credentials/genetic-scheduller");

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     */
    private static final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() {
        // Load client secrets.
        InputStream in = CalendarApiImpl.class.getClassLoader().getResourceAsStream("client_secret.json");
        GoogleClientSecrets clientSecrets = null;
        GoogleAuthorizationCodeFlow flow = null;
        Credential credential = null;
        try {
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            // Build flow and trigger user authorization request.
            flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                    .setDataStoreFactory(DATA_STORE_FACTORY)
                    .setAccessType("offline")
                    .build();
            credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return credential;
    }

    /**
     * Build and return an authorized Calendar client service.
     *
     * @return an authorized Calendar client service
     * @throws IOException
     */
    public static com.google.api.services.calendar.Calendar getCalendarService() {
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Override
    public void addEvent(String name, DateTime startDate, int duration) throws IOException {
        Event event = new Event()
                .setSummary(name)
                .setStart(convertDateTimeToEventDateTime(startDate));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, duration);
        event.setEnd(convertDateToEventDateTime(calendar.getTime()));

        service.events().insert(CALENDAR_ID, event).execute();
    }

    private EventDateTime convertDateToEventDateTime(Date date) {
        return new EventDateTime().setDateTime(new DateTime(date)).setTimeZone(TIME_ZONE);
    }

    private EventDateTime convertDateTimeToEventDateTime(DateTime dateTime) {
        return new EventDateTime().setDateTime(dateTime).setTimeZone(TIME_ZONE);
    }

    @Override
    public void addEvent(String name, DateTime startDate, DateTime endDate) throws IOException {
        Event event = new Event()
                .setSummary(name)
                .setStart(convertDateTimeToEventDateTime(startDate))
                .setEnd(convertDateTimeToEventDateTime(endDate));

        service.events().insert(CALENDAR_ID, event).execute();
    }

    @Override
    public void addEvent(Event event) {
        try {
            service.events().insert(CALENDAR_ID, event).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Event> getAllEvents() throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list(CALENDAR_ID)
                .setTimeMin(now)
                //                       .setTimeMax(endDate) // TODO: set TimeMax to end of semester
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events != null ? events.getItems() : new ArrayList<>();
    }

    @Override
    public List<Event> getNFirstEvents(int amount) throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list(CALENDAR_ID)
                .setMaxResults(amount)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events != null ? events.getItems() : new ArrayList<>();
    }

    @Override
    public List<Event> getEventsBetweenDates(DateTime startDate, DateTime endDate) throws IOException {
        Events events = service.events().list(CALENDAR_ID)
                .setTimeMin(startDate)
                .setTimeMax(endDate)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events != null ? events.getItems() : new ArrayList<>();
    }

    @Override
    public Event getEvent(String eventId) throws IOException {
        return service.events().get(CALENDAR_ID, eventId).execute();
    }

    @Override
    public void deleteEvent(String eventId) throws IOException {
        service.events().delete(CALENDAR_ID, eventId).execute();
    }

    @Override
    public void deleteEvent(Event event) throws IOException {
        service.events().delete(CALENDAR_ID, event.getId()).execute();
    }

    @Override
    public void editEvent(Event event) throws IOException {
        service.events().update(CALENDAR_ID, event.getId(), event).execute();
    }

}
