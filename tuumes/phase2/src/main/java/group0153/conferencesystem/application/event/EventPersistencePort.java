package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;

import java.util.List;
import java.util.Optional;

/**
 * An interface class which describes how event data is saved and changed in the database
 */

public interface EventPersistencePort {

    /**
     * Saves the event to the database
     * @param event The event which is being saved.
     */
    void saveEvent(Event event);

    /**
     * Finds an event id in the database.
     * @param eventId The event id which is searched for.
     * @return The event entity if one is found.
     */
    Optional<Event> findById(String eventId);

    /**
     * Gets a list of all events at the conference.
     * @return A list of all events at the conference.
     */
    List<Event> getAllEvents();

    /**
     * Registers a user to an event by their user id.
     * @param eventId The event id which a user is being added to.
     * @param userId The user id which is added to the event.
     */
    void registerUserById(String eventId, String userId);

    /**
     * Registers a speaker to an event.
     * @param eventId The event id in which the speaker is added to.
     * @param userId The speaker id that is added to an event.
     */
    void registerSpeakerById(String eventId, String userId);

    /**
     * Unregister a user from an event.
     * @param eventId The event id in which the speaker is added to.
     * @param userId The speaker id that is added to an event.
     */
    void unregisterUserById(String eventId, String userId);
}
