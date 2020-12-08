package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.EventNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Handles the updating of data members in the Event entity
 */
public class EventUpdater(){
    EventPersistencePort eventPersistencePort;

    public EventUpdater(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    private void updateCapacity(String eventId, int newCapacity){
        Optional<Event> event = getEventById(eventId);
        if (!event.isPresent()){
            throw new EventNotFoundException()
        }
        Event event = optionalEvent.get();
        event.setUserLimit(newCapacity);
    }
}
