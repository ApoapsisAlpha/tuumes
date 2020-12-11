package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.application.exceptions.EventNotFoundException;
import group0153.conferencesystem.application.exceptions.UnsuccessfulCommandException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

/**
 * EventScheduler that handles the updating of data members in the Event entity
 */
public class EventUpdater {
    EventPersistencePort eventPersistencePort;

    /**
     * Instantiates an EventUpdater.
     * @param eventPersistencePort how the events are saved to the database.
     */
    public EventUpdater(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     * Get event based on event id.
     * @param eventId The event id of the event.
     * @return The event with the given event id.
     * @throws EventNotFoundException Thrown when no event have the event id provided.
     */
    private Event getEvent(String eventId) throws EventNotFoundException {
        Optional<Event> optionalEvent = this.eventPersistencePort.getEvent(eventId);
        if (!optionalEvent.isPresent()) {
            throw new EventNotFoundException();
        }
        return optionalEvent.get();
    }

    /**
     * Updates the capacity of an event.
     * @param eventId The id of the event to change capacity.
     * @param newCapacity New capacity of the event.
     * @throws EventNotFoundException Thrown when no event have the event id provided.
     */
    public void updateCapacity(String eventId, int newCapacity) throws EventNotFoundException {
        Event event = this.getEvent(eventId);
        event.setUserLimit(newCapacity);
    }

    /**
     * Updated the description of an event.
     * @param eventId The id of the event to change the description.
     * @param newDescription New description of the event.
     * @throws EventNotFoundException Thrown when no event have the event id provided.
     */
    private void updateDescription(String eventId, String newDescription) throws EventNotFoundException {
        Event event = this.getEvent(eventId);
        event.setDescription(newDescription);
    }

    /**
     * Update the start time of an event.
     * @param eventId The id of the event to change the start time.
     * @param newStartTime New start time of the event.
     * @throws UnsuccessfulCommandException Thrown when the event has time conflict with another event which
     *                                      uses the same room.
     */
    private void updateStartTime(String eventId, LocalDateTime newStartTime) throws UnsuccessfulCommandException {
        Event event = this.getEvent(eventId);
        ArrayList<Event> events = this.eventPersistencePort.getAllEvents();
        for (Event otherEvent : events) {
            if (otherEvent.getRoomId().equals(event.getRoomId()) && otherEvent.getEndTime().isAfter(newStartTime))
                throw new UnsuccessfulCommandException("There is a time conflict with the new start time.");
        }
        event.setStartTime(newStartTime);
    }

    /**
     * Update the end time of an event.
     * @param eventId The id of the event to change the end time.
     * @param newEndTime New end time of the event.
     * @throws UnsuccessfulCommandException Thrown when the event has time conflict with another event which
     *                                      uses the same room.
     */
    private void updateEndTime(String eventId, LocalDateTime newEndTime) throws UnsuccessfulCommandException {
        Event event = this.getEvent(eventId);
        ArrayList<Event> events = this.eventPersistencePort.getAllEvents();
        for (Event otherEvent : events) {
            if (otherEvent.getRoomId().equals(event.getRoomId()) && otherEvent.getStartTime().isBefore(newEndTime))
                throw new UnsuccessfulCommandException("There is a time conflict with the new end time.");
        }
        event.setEndTime(newEndTime);
    }
}
