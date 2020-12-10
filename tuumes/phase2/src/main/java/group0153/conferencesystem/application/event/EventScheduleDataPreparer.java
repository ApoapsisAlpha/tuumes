package group0153.conferencesystem.application.event;

import group0153.conferencesystem.adapters.gateways.event.EventModel;
import group0153.conferencesystem.application.EventData;
import group0153.conferencesystem.entities.event.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Creates array lists of EventData representing the schedule (in sorted order by preference).
 */
public class EventScheduleDataPreparer {
    EventPersistencePort eventPersistencePort;

    EventScheduleDataPreparer(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     *
     * @param date Date to be checked.
     * @param lDate The earliest date in the interval.
     * @param rDate The latest date in the interval.
     * @return Returns true iff date occurs between lDate and rDate (inclusive of endpoints).
     */
    private boolean inTimeInterval(Date date, Date lDate, Date rDate) {
        return !date.before(lDate) && !date.after(rDate);
    }

    /**
     *
     * @param events An array list of the events to be turned into event data.
     * @return An arraylist in the same order as events with the event entities
     * transformed into event data.
     */
    private ArrayList<EventData> createEventDataArray(ArrayList<Event> events) {
        ArrayList<EventData> res = new ArrayList<>();
        for (Event event : events) {
            res.add(new EventData(event));
        }
        return res;
    }

    /**
     *
     * @param lDate The earliest date in the time interval.
     * @param rDate The latest date in the time interval.
     * @return Returns an array list of EventData sorted by the start time of the event. The events
     * all occur and end within lDate and rDate (inclusive).
     */
    ArrayList<EventData> getScheduleModelByInInterval(Date lDate, Date rDate) {
        ArrayList<Event> events = eventPersistencePort.getAllEvents();
        ArrayList<Event> eventsInInterval = new ArrayList<>();
        for (Event event : events) {
            if (this.inTimeInterval(event.getStartTime(), lDate, rDate) && this.inTimeInterval(event.getEndTime(), lDate, rDate)) {
                eventsInInterval.add(event);
            }
        }
        eventsInInterval.sort(new SortEventsByDate());
        return this.createEventDataArray(eventsInInterval);
    }

    /**
     *
     * @return Returns all of the upcoming events that are scheduled to happen.
     */
    ArrayList<EventData> getAllUpcomingEvents() {
        ArrayList<Event> events = eventPersistencePort.getAllEvents();
        Date now = new Date();
        ArrayList<Event> upcomingEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getStartTime().after(now))
                upcomingEvents.add(event);
        }
        upcomingEvents.sort(new SortEventsByDate());
        return this.createEventDataArray(upcomingEvents);
    }
}
