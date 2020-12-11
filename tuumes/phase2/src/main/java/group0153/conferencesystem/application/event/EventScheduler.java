package group0153.conferencesystem.application.event;

import group0153.conferencesystem.application.event.data.EventData;
import group0153.conferencesystem.application.room.RoomManager;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;


/**
 * A event use case class that handles the scheduling of events.
 */
public class EventScheduler {
    EventPersistencePort eventPersistencePort;
    RoomManager roomManager;

    /**
     * Instantiates an EventScheduler.
     * @param eventPersistencePort How the events are saved to the database.
     * @param roomManager The room manager for event rooms.
     */
    public EventScheduler(EventPersistencePort eventPersistencePort, RoomManager roomManager) {
        this.eventPersistencePort = eventPersistencePort;
        this.roomManager = roomManager;
    }

    /**
     *
     * @param id The id of the event.
     * @param eventName The event name.
     * @param description The event description.
     * @param startTime The start time of the event given by the unix timestamp.
     * @param endTime The end time of the event given by the unix timestamp.
     * @param roomId The id of the room that this event takes place in.
     * @param speakerIds The list of speakers who are to attend this event.
     * @param userLimit The maximum amount of people that can attend this event.
     * @param isVipOnlyEvent If this event is only for vip people or not.
     * @return A new event entity with the above attributes.
     */
    private Event createEvent(String id, String eventName, String description, long startTime, long endTime,
                              String roomId, ArrayList<String> speakerIds, int userLimit, boolean isVipOnlyEvent) {
        startTime *= 1000;
        endTime *= 1000;
        LocalDateTime st = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), TimeZone.getDefault().toZoneId());
        LocalDateTime et = LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), TimeZone.getDefault().toZoneId());
        return new Event(id, eventName, description, st, et, roomId, speakerIds, userLimit, isVipOnlyEvent);
    }

    /**
     * Returns all active events. (Ignores events that have already passed)
     *
     * @return Arraylist of events.
     */
    private ArrayList<String> getScheduledEventIds() {
        LocalDateTime current = LocalDateTime.now();
        ArrayList<String> result = new ArrayList<>();
        for (Event event : eventPersistencePort.getAllEvents()) {
            if (!event.getStartTime().isBefore(current)) {
                result.add(event.getId());
            }
        }
        return result;
    }

    /**
     * Tries to schedule the event.
     *
     * @param event: The event to be scheduled.
     * @throws UnsuccessfulCommandException The event could not be added successfully.
     * @return Returns the EventData for the new event after it has been successfully added.
     */
    private EventData scheduleEventHelper(Event event) throws UnsuccessfulCommandException {
        ArrayList<String> eventIds = getScheduledEventIds();
        for (String otherEventId : eventIds) {
            if (hasTimeConflict(event.getId(), otherEventId)) {
                throw new UnsuccessfulCommandException("The event to be scheduled has a time conflict.");
            }
        }
        this.eventPersistencePort.saveEvent(event);
        return new EventData(event, this.roomManager.getRoomById(event.getRoomId()));
    }

    /**
     *
     * @param eventName       The name of the event.
     * @param description     The event description.
     * @param startTime       The start time of the event given by the unix timestamp.
     * @param endTime         The end time of the event given by the unix timestamp.
     * @param roomId          The id of the room that this event takes place in.
     * @param speakerIds      The list of speakers who are to attend this event.
     * @param userLimit       The maximum amount of people that can attend this event.
     * @param isVipOnlyEvent  If this event is only for vip people or not.
     * @return The EventData for the new event after it has been successfully added.
     * @throws UnsuccessfulCommandException if the event could not be added successfully.
     */
    public EventData scheduleEvent(String eventName, String description, long startTime, long endTime,
                         String roomId, ArrayList<String> speakerIds, int userLimit, boolean isVipOnlyEvent) throws UnsuccessfulCommandException {
        Event event = this.createEvent(UUID.randomUUID().toString(), eventName, description, startTime, endTime, roomId, speakerIds, userLimit, isVipOnlyEvent);
        return this.scheduleEventHelper(event);
    }

    /**
     * Check if two events have time conflict, two events have time conflict if they are using the same room and have
     * overlapped duration.
     *
     * @param eventId1 event id of the first event.
     * @param eventId2 event id of the second event.
     * @return True if and only if the first event and the second event have time conflict and using the same room.
     */
    private boolean hasTimeConflict(String eventId1, String eventId2) {
        Optional<Event> event1 = this.eventPersistencePort.getEvent(eventId1);
        Optional<Event> event2 = this.eventPersistencePort.getEvent(eventId2);
        if (!event1.isPresent() || !event2.isPresent()) {
            return false;
        }
        Event event11 = event1.get();
        Event event22 = event2.get();
        if (!event11.getRoomId().equals(event22.getRoomId())) return false;
        if (event11.getStartTime().equals(event22.getStartTime()))
            return true;
        if (event11.getStartTime().isBefore(event22.getStartTime())) {
            return event11.getEndTime().isAfter(event22.getStartTime());
        }
        return event11.getStartTime().isBefore(event22.getEndTime());
    }

    /**
     * Tries to remove the event by its id (if an event with this id exists).
     *
     * @param eventId The id of the event to be removed.
     * @return An arraylist of the id of the users who were registered for this event.
     * @throws UnsuccessfulCommandException if the event could not be found.
     */
    public ArrayList<String> unScheduleEvent(String eventId) throws UnsuccessfulCommandException {
        Optional<Event> event = this.eventPersistencePort.getEvent(eventId);
        if (!event.isPresent()) throw new UnsuccessfulCommandException("The event could not be found.");
        Event e = event.get();
        ArrayList<String> res = e.getUserIds();
        res.addAll(e.getSpeakerIds());
        this.eventPersistencePort.deleteEvent(eventId);
        return res;
    }
}
