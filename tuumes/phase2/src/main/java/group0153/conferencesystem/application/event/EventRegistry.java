package group0153.conferencesystem.application.event;
import group0153.conferencesystem.entities.user.UserType;
import group0153.conferencesystem.exceptions.eventExceptions.EventNotFoundException;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;
import java.util.Optional;

/**
 * A event use case class that handles the registration of users for events (from the events perspective).
 */
public class EventRegistry {

    EventPersistencePort eventPersistencePort;

    /**
     * Instantiates an EventRegistry.
     * @param eventPersistencePort How the events are saved to the database.
     */
    public EventRegistry(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     * Check if the given user has already registered to the given event.
     * @param eventId The event id of the event.
     * @param userId The user id of the user.
     * @return True if the user id has been remove from the userId list of the event, false if the user Id is not in the list.
     * @throws EventNotFoundException No event found.
     */
    private boolean alreadyRegistered(String eventId, String userId) throws EventNotFoundException {
        Event event = this.getEvent(eventId);
        for (String registeredId : event.getUserIds()) {
            if (registeredId.equals(userId)) return true;
        }
        return false;
    }

    /**
     *
     * @param event The event to be checked.
     * @return True if the event has any spots left. False otherwise.
     */
    private boolean hasSpotsLeft(Event event) {
        return event.getUserCount() < event.getUserLimit();
    }

    /**
     * Add a user id to the userId list of a event. (when register a user to an event.)
     * @param eventId The id of the event.
     * @param userId The id of the user.
     * @return True if the user id is added to the userId list of the event. Return false otherwise (user already
     * registered to this event or user limit is reached).
     * @throws EventNotFoundException No event found.
     * @throws UnsuccessfulCommandException User could not be registered.
     */
    public boolean addUserIdToEventUserIdList(String eventId, String userId, UserType userType) throws UnsuccessfulCommandException {
        Event event = this.getEvent(eventId);
        if (alreadyRegistered(event.getId(), userId) || !this.hasSpotsLeft(event)) return false;
        if (userType != UserType.VIP && event.isVipOnlyEvent()) return false;
        event.addUserId(userId);
        event.increaseUserCount(1);
        return true;
    }

    /**
     * Remove a user id from the userId list of a event. (when unregister a user to an event.)
     * @param eventId The id of the event.
     * @param userId The id of the user.
     * @return True if the user id is removed from the userId list of the event. Return false otherwise (user was not
     * registered to this event).
     * @throws EventNotFoundException No event found.
     */
    public boolean removeUserIdFromEventUserIdList(String eventId, String userId) throws EventNotFoundException {
        Event event = this.getEvent(eventId);
        if (!alreadyRegistered(eventId, userId)) return false;
        event.removeUserId(userId);
        event.decreaseUserCount(1);
        return true;
    }

    /**
     *
     * @param speakerId The id of the speaker to be registered.
     * @param eventId The id of the event that the speaker is supposed to be registered to.
     * @throws UnsuccessfulCommandException The speaker could not be registered.
     */
    public void registerSpeakerForEvent(String speakerId, String eventId) throws UnsuccessfulCommandException {
        for (Event event : eventPersistencePort.getAllEvents()){
            if (event.getId().equals(eventId)) {
                event.addSpeakerId(speakerId);
            }
        }
    }

    /**
     *
     * @param eventId The id of the event to be returned.
     * @return The event.
     * @throws EventNotFoundException The event could not be found.
     */
    private Event getEvent(String eventId) throws EventNotFoundException {
        Optional<Event> optionalEvent = this.eventPersistencePort.getEvent(eventId);
        if (!optionalEvent.isPresent())
            throw new EventNotFoundException();
        return optionalEvent.get();
    }
}
