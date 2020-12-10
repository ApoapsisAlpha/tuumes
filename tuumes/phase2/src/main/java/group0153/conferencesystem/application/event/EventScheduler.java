package group0153.conferencesystem.application.event;

import group0153.conferencesystem.application.event.data.EventData;
import group0153.conferencesystem.application.room.RoomManager;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


/**
 * Handles the scheduling of events.
 */
public class EventScheduler {
    EventPersistencePort eventPersistencePort;
    RoomManager roomManager;

    public EventScheduler(EventPersistencePort eventPersistencePort, RoomManager roomManager) {
        this.eventPersistencePort = eventPersistencePort;
        this.roomManager = roomManager;
    }

    /**
     * Returns all active events. (Ignores events that have already passed)
     *
     * @return Arraylist of events.
     */
    private ArrayList<String> getScheduledEventIds() {
        Date current = new Date();
        ArrayList<String> result = new ArrayList<String>();
        for (Event event : eventPersistencePort.getAllEvents()) {
            if (!event.getStartTime().before(current)) {
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
    public EventData scheduleEvent(Event event) throws UnsuccessfulCommandException {
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
     * Check if two events have time conflict.
     *
     * @param eventId1 event id of the first event.
     * @param eventId2 event id of the second event.
     * @return True if the first event and the second event have time conflict.
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
        if (event11.getStartTime().before(event22.getStartTime())) {
            return event11.getEndTime().after(event22.getStartTime());
        }
        return event11.getStartTime().before(event22.getEndTime());
    }

    /**
     * Tries to remove the event by its id (if an event with this id exists).
     *
     * @param eventId The id of the event to be removed.
     */
    public void unscheduleEvent(String eventId) throws UnsuccessfulCommandException {
        Optional<Event> event = this.eventPersistencePort.getEvent(eventId);
        if (!event.isPresent()) throw new UnsuccessfulCommandException("The event could not be found.");
        this.eventPersistencePort.deleteEvent(eventId);
    }
}
