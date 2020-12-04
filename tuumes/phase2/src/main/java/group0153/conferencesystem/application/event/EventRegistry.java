package group0153.conferencesystem.application.event;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.CommandException;

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
     */
    public void registerUserForEvent(String userId, String eventId) throws CommandException {
        for (Event event : this.events) {
            if (event.getId().equals(eventId)) {
                ArrayList<String> userIds = event.getUserIds();
                for (String id : userIds) {
                    if (id.equals(userId)) throw new CommandException("User is already registered for this event.");
                }
                if (event.getUserCount() < event.getUserLimit()) {
                    event.addUserId(userId);
                    event.increaseUserCount(1);
                } else {
                    throw new CommandException("This event's user limit has already been reached.");
                }
            }
        }
    }

    /**
     *
     * @param speakerId The id of the speaker to be registered.
     * @param eventId The id of the event that the speaker is supposed to be registered to.
     * @throws CommandException The speaker could not be registered.
     */
    public void registerSpeakerForEvent(String speakerId, String eventId) throws CommandException {
        for (Event event : this.events) {
            if (event.getId().equals(eventId)) {
                event.addSpeakerId(speakerId);
            }
        }
    }
}
