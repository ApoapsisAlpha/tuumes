package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;

import java.util.List;
import java.util.Optional;


public interface EventPersistencePort {

    void saveEvent(Event event);

    Optional<Event> findById(String eventId);

    List<Event> getAllEvents();

    void registerUserById(String eventId, String userId);

    void registerSpeakerById(String eventId, String userId);
}
