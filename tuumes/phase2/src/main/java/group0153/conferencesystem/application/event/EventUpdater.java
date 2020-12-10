package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.EventNotFoundException;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Handles the updating of data members in the Event entity
 */
public class EventUpdater {
    EventPersistencePort eventPersistencePort;

    public EventUpdater(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    private Event getEvent(String eventId) throws EventNotFoundException {
        Optional<Event> optionalEvent = this.eventPersistencePort.getEvent(eventId);
        if (!optionalEvent.isPresent()) {
            throw new EventNotFoundException();
        }
        return optionalEvent.get();
    }

    private void updateCapacity(String eventId, int newCapacity) throws EventNotFoundException {
        Event event = this.getEvent(eventId);
        event.setUserLimit(newCapacity);
    }

    private void updateDescription(String eventId, String newDescription) throws EventNotFoundException {
        Event event = this.getEvent(eventId);
        event.setDescription(newDescription);
    }

    private void updateStartTime(String eventId, Date newStartTime) throws UnsuccessfulCommandException {
        Event event = this.getEvent(eventId);
        ArrayList<Event> events = this.eventPersistencePort.getAllEvents();
        for (Event otherEvent : events) {
            if (otherEvent.getRoomId().equals(event.getRoomId()) && otherEvent.getEndTime().after(newStartTime))
                throw new UnsuccessfulCommandException("There is a time conflict with the new start time.");
        }
        event.setStartTime(newStartTime);
    }

    private void updateEndTime(String eventId, Date newEndTime) throws UnsuccessfulCommandException {
        Event event = this.getEvent(eventId);
        ArrayList<Event> events = this.eventPersistencePort.getAllEvents();
        for (Event otherEvent : events) {
            if (otherEvent.getRoomId().equals(event.getRoomId()) && otherEvent.getStartTime().before(newEndTime))
                throw new UnsuccessfulCommandException("There is a time conflict with the new start time.");
        }
        event.setEndTime(newEndTime);
    }

    public void update(String eventId) throws EventNotFoundException {
        Event event = this.getEvent(eventId);
    }
}
