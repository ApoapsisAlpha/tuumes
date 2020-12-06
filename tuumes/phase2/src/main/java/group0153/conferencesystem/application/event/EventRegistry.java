package group0153.conferencesystem.application.event;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;

/**
 * Handles the registration of users for events (from the events perspective).
 */
public class EventRegistry {
    private final ArrayList<Event> events;

    /**
     *
     * @param events The list of events that are currently scheduled.
     */
    public EventRegistry(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     *
     * @param userId The id of the user to be registered.
     * @param eventId The id of the event that the user is to be registered for.
     * @throws UnsuccessfulCommandException The user could not be registered.
     */
    public void registerUserForEvent(String userId, String eventId) throws UnsuccessfulCommandException {
        for (Event event : this.events) {
            if (event.getId().equals(eventId)) {
                ArrayList<String> userIds = event.getUserIds();
                for (String id : userIds) {
                    if (id.equals(userId)) throw new UnsuccessfulCommandException("User is already registered for this event.");
                }
                if (event.getUserCount() < event.getUserLimit()) {
                    event.addUserId(userId);
                    event.increaseUserCount(1);
                } else {
                    throw new UnsuccessfulCommandException("This event's user limit has already been reached.");
                }
            }
        }
    }

    /**
     *
     * @param speakerId The id of the speaker to be registered.
     * @param eventId The id of the event that the speaker is supposed to be registered to.
     * @throws UnsuccessfulCommandException The speaker could not be registered.
     */
    public void registerSpeakerForEvent(String speakerId, String eventId) throws UnsuccessfulCommandException {
        for (Event event : this.events) {
            if (event.getId().equals(eventId)) {
                if (event.getUserCount() < event.getUserLimit()) {
                    event.addSpeakerId(speakerId);
                    event.increaseUserCount(1);
                } else {
                    throw new UnsuccessfulCommandException("This event's user limit has already been reached.");
                }
            }
        }
    }

    /**
     * Does nothing if the user was not registered in the event to begin with.
     * @param userId The id of the user to be removed.
     * @param eventId The id of the event that the user is to be removed from.
     * @throws UnsuccessfulCommandException The event could not be found.
     */
    public void unregisterUserForEvent(String userId, String eventId) throws UnsuccessfulCommandException {
        for (Event event : this.events) {
            if (event.getId().equals(eventId)) {
                boolean res = event.removeUserId(userId);
                if (res) event.decreaseUserCount(1);
                return;
            }
        }
        throw new UnsuccessfulCommandException("The event could not be found.");
    }
}
