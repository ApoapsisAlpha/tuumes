//package group0153.conferencesystem.application.event;
//
//import group0153.conferencesystem.application.event.data.EventData;
//import group0153.conferencesystem.application.room.RoomManager;
//import group0153.conferencesystem.entities.event.Event;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * A event use case class that creates array lists of EventData representing the
// * schedule (in sorted order by preference).
// */
//public class EventScheduleDataPreparer {
//    private final EventPersistencePort eventPersistencePort;
//    private final RoomManager roomManager;
//
//    /**
//     * Instantiates an EventScheduleDataPreparer.
//     * @param eventPersistencePort How the events are saved to the database.
//     * @param roomManager The room manager for event rooms.
//     */
//    public EventScheduleDataPreparer(EventPersistencePort eventPersistencePort, RoomManager roomManager) {
//        this.eventPersistencePort = eventPersistencePort;
//        this.roomManager = roomManager;
//    }
//
//    /**
//     *
//     * @param date Date to be checked.
//     * @param earliestDate The earliest date in the interval.
//     * @param latestDate The latest date in the interval.
//     * @return Returns true iff date occurs between earliestDate and latestDate (inclusive of endpoints).
//     */
//    private boolean inTimeInterval(LocalDateTime date, LocalDateTime earliestDate, LocalDateTime latestDate) {
//        return !date.isBefore(earliestDate) && !date.isAfter(latestDate);
//    }
//
//    /**
//     *
//     * @param events An array list of the events to be turned into event data.
//     * @return An arraylist in the same order as events with the event entities
//     * transformed into event data.
//     */
//    private ArrayList<EventData> createEventDataArray(ArrayList<Event> events) {
//        ArrayList<EventData> res = new ArrayList<>();
//        for (Event event : events) {
//            res.add(new EventData(event, this.roomManager.getRoomById(event.getRoomId())));
//        }
//        return res;
//    }
//
//    /**
//     *
//     * @return A sorted array list of all of the upcoming events.
//     */
//    private ArrayList<Event> getUpcomingEventsHelper() {
//        ArrayList<Event> events = eventPersistencePort.getAllEvents();
//        LocalDateTime now = LocalDateTime.now();
//        ArrayList<Event> upcomingEvents = new ArrayList<>();
//        for (Event event : events) {
//            if (event.getStartTime().isAfter(now))
//                upcomingEvents.add(event);
//        }
//        upcomingEvents.sort(new SortEventsByDate());
//        return upcomingEvents;
//    }
//
//    /**
//     *
//     * @param earliestDate The earliest date in the time interval.
//     * @param latestDate The latest date in the time interval.
//     * @return Returns an array list of EventData sorted by the start time of the event. The events
//     * all occur and end within earliestDate and latestDate (inclusive).
//     */
//    public ArrayList<EventData> getScheduleModelByInInterval(LocalDateTime earliestDate, LocalDateTime latestDate) {
//        ArrayList<Event> events = eventPersistencePort.getAllEvents();
//        ArrayList<Event> eventsInInterval = new ArrayList<>();
//        for (Event event : events) {
//            if (this.inTimeInterval(event.getStartTime(), earliestDate, latestDate) &&
//                    this.inTimeInterval(event.getEndTime(), earliestDate, latestDate)) {
//                eventsInInterval.add(event);
//            }
//        }
//        eventsInInterval.sort(new SortEventsByDate());
//        return this.createEventDataArray(eventsInInterval);
//    }
//
//
//
//    /**
//     *
//     * @return Returns all of the upcoming events that are scheduled to happen.
//     */
//    public ArrayList<EventData> getUpcomingEvents() {
//        ArrayList<Event> events = this.getUpcomingEventsHelper();
//        return this.createEventDataArray(events);
//    }
//
//    /**
//     *
//     * @param eventIds The list of ids of events to be excluded from the schedule.
//     * @return A sorted list of event data representing the schedule of upcoming events.
//     */
//    public ArrayList<EventData> getUpcomingEventsExcluding(List<String> eventIds) {
//        ArrayList<Event> events = getUpcomingEventsHelper();
//        ArrayList<Event> res = new ArrayList<>();
//        for (Event event : events) {
//            boolean add = true;
//            for (String id : eventIds) {
//                if (event.getId().equals(id)) {
//                    add = false;
//                    break;
//                }
//            }
//            if (add)
//                res.add(event);
//        }
//        return this.createEventDataArray(res);
//    }
//}
