package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;

import java.util.ArrayList;
import java.util.Optional;

public interface EventPersistencePort {

    void saveEvent(Event event);

    void deleteEvent(String eventId);

    // TODO: return event entity here, or pull the event entity from adapter layer?

    Optional<Event> getEvent(String eventId);

    ArrayList<Event> getAllEvents();

}
