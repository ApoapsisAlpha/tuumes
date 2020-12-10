package group0153.conferencesystem.application.event;

import group0153.conferencesystem.application.event.data.EventData;
import group0153.conferencesystem.application.room.RoomManager;
import group0153.conferencesystem.entities.event.Event;

import java.util.*;

/**
 * Creates array lists of EventData representing the schedule (in sorted order by preference).
 */
public class EventScheduleDataPreparer {
    private final EventPersistencePort eventPersistencePort;
    private final RoomManager roomManager;

    EventScheduleDataPreparer(EventPersistencePort eventPersistencePort, RoomManager roomManager) {
        this.eventPersistencePort = eventPersistencePort;
        this.roomManager = roomManager;
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
            res.add(new EventData(event, this.roomManager.getRoomById(event.getRoomId())));
        }
        return res;
    }

    /**
     *
     * @return A sorted array list of all of the upcoming events.
     */
    private ArrayList<Event> getUpcomingEventsHelper() {
        ArrayList<Event> events = eventPersistencePort.getAllEvents();
        Date now = new Date();
        ArrayList<Event> upcomingEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getStartTime().after(now))
                upcomingEvents.add(event);
        }
        upcomingEvents.sort(new SortEventsByDate());
        return upcomingEvents;
    }

    /**
     *
     * @param lDate The earliest date in the time interval.
     * @param rDate The latest date in the time interval.
     * @return Returns an array list of EventData sorted by the start time of the event. The events
     * all occur and end within lDate and rDate (inclusive).
     */
    public ArrayList<EventData> getScheduleModelByInInterval(Date lDate, Date rDate) {
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
    public ArrayList<EventData> getUpcomingEvents() {
        ArrayList<Event> events = this.getUpcomingEventsHelper();
        return this.createEventDataArray(events);
    }

    /**
     *
     * @param eventIds The list of ids of events to be excluded from the schedule.
     * @return A sorted list of event data representing the schedule of upcoming events.
     */
    public ArrayList<EventData> getUpcomingEventsExcluding(List<String> eventIds) {
        ArrayList<Event> events = getUpcomingEventsHelper();
        ArrayList<Event> res = new ArrayList<>();
        for (Event event : events) {
            boolean add = true;
            for (String id : eventIds) {
                if (event.getId().equals(id)) {
                    add = false;
                    break;
                }
            }
            if (add)
                res.add(event);
        }
        return this.createEventDataArray(res);
    }
}
