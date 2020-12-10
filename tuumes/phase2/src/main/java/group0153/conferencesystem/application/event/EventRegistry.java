package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.EventNotFoundException;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.Optional;

/**
 * Handles the registration of users for events (from the events perspective).
 */
public class EventRegistry {

    EventPersistencePort eventPersistencePort;

    /**
     * Construct an instance of EventRegistery using the provided EventPersistencePort instance.
     *
     * @param eventPersistencePort an instance of EventPersistencePort to be used to save data
     */
    public EventRegistry(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     * Private method. Return an event based on its id.
     *
     * @param eventId The id of the event.
     * @return A reference to the event if the event exists, otherwise returns null
     */
    private Optional<Event> getEventById(String eventId) {
        return eventPersistencePort.getEvent(eventId);
    }

    /**
     * Check if the given user has already registered to the given event.
     *
     * @param eventId The event id of the event.
     * @param userId  The user id of the user.
     * @return True if the user id has been remove from the userId list of the event, false if the user Id is not in the list.
     * @throws EventNotFoundException No event found.
     */
    private boolean alreadyRegistered(String eventId, String userId) throws EventNotFoundException {
        Optional<Event> event = getEventById(eventId);
        if (!event.isPresent())
            throw new EventNotFoundException();
        for (String registeredId : event.get().getUserIds()) {
            if (registeredId.equals(userId)) return true;
        }
        return false;
    }

    /**
     * Add a user id to the userId list of a event. (when register a user to an event.)
     *
     * @param eventId The id of the event.
     * @param userId  The id of the user.
     * @throws EventNotFoundException No event found.
     */
    public void registerUserForEvent(String eventId, String userId) throws UnsuccessfulCommandException {
        Optional<Event> optionalEvent = getEventById(eventId);
        if (!optionalEvent.isPresent())
            throw new EventNotFoundException();
        Event event = optionalEvent.get();
        if (alreadyRegistered(event.getId(), userId))
            throw new UnsuccessfulCommandException("User already registered.");
        if (!event.hasSpotsLeft())
            throw new UnsuccessfulCommandException("Event's user limit has already been reached.");
        event.addUserId(userId);
        event.increaseUserCount(1);
    }

    /**
     * Remove a user id from the userId list of a event. (when unregister a user to an event.)
     *
     * @param eventId The id of the event.
     * @param userId  The id of the user.
     * @throws UnsuccessfulCommandException The command was unsuccessful. Either user is already registered
     *                                      or the event could not be found.
     */
    public void unregisterUserFromEvent(String eventId, String userId) throws UnsuccessfulCommandException {
        Optional<Event> optionalEvent = getEventById(eventId);
        if (!optionalEvent.isPresent())
            throw new EventNotFoundException();
        Event event = optionalEvent.get();
        if (!alreadyRegistered(eventId, userId)) throw new UnsuccessfulCommandException("User is already registered.");
        event.removeUserId(userId);
        event.decreaseUserCount(1);
    }

    /**
     * @param speakerId The id of the speaker to be registered.
     * @param eventId   The id of the event that the speaker is supposed to be registered to.
     * @throws UnsuccessfulCommandException The speaker could not be registered.
     */
    public void registerSpeakerForEvent(String speakerId, String eventId) throws UnsuccessfulCommandException {
        Optional<Event> optionalEvent = getEventById(eventId);
        if (!optionalEvent.isPresent()) throw new EventNotFoundException();
        Event event = optionalEvent.get();
        if (event.hasSpotsLeft()) {
            event.addSpeakerId(speakerId);
            event.increaseUserCount(1);
        }
    }

    /**
     * @param speakerId The id of the speaker to be removed.
     * @param eventId   The event for the speaker to be removed from.
     * @throws UnsuccessfulCommandException The removal was unsuccessful.
     */
    public void unregisterSpeakerFromEvent(String speakerId, String eventId) throws UnsuccessfulCommandException {
        Optional<Event> optionalEvent = getEventById(eventId);
        if (!optionalEvent.isPresent()) throw new EventNotFoundException();
        Event event = optionalEvent.get();
        event.removeSpeakerId(speakerId);
    }
}
