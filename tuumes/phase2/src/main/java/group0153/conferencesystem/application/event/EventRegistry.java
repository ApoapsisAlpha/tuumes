package group0153.conferencesystem.application.event;
import group0153.conferencesystem.application.event.exception.EventNotFoundException;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.CommandException;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Handles the registration of users for events (from the events perspective).
 */
public class EventRegistry {

    EventPersistencePort eventPersistencePort;

    public EventRegistry(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     * Private method. Return an event based on its id.
     * @param eventId The id of the event.
     * @return A reference to the event if the event exists, otherwise returns null
     */
    private Optional<Event> getEventById(String eventId){
        return eventPersistencePort.findById(eventId);
    }


//    /**
//     *
//     * @param events The list of events that are currently scheduled.
 //    */
//    public EventRegistry(ArrayList<Event> events) {
//        this.events = events;
//    }
//
//    /**
//     *
//     * @param userId The id of the user to be registered.
//     * @param eventId The id of the event that the user is to be registered for.
//     */
//    public void registerUserForEvent(String userId, String eventId) throws CommandException {
//        for (Event event : this.events) {
//            if (event.getId().equals(eventId)) {
//                ArrayList<String> userIds = event.getUserIds();
//                for (String id : userIds) {
//                    if (id.equals(userId)) throw new CommandException("User is already registered for this event.");
//                }
//                if (event.getUserCount() < event.getUserLimit()) {
//                    event.addUserId(userId);
//                    event.increaseUserCount(1);
//                } else {
//                    throw new CommandException("This event's user limit has already been reached.");
//                }
//            }
//        }
//    }
    /**
     * Return an event's user count based on its id.
     * @param eventId The id of the event.
     * @return The user count of the event if the event exists, otherwise returns -1.
     */
    private int getUserCountById(String eventId){
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::getUserCount).orElse(-1);
    }

    /**
     * Check if the given user has already registered to the given event.
     * @param eventId The event id of the event.
     * @param userId The user id of the user.
     * @return True if the user id has been remove from the userId list of the event, false if the user Id is not in the list.
     * @throws EventNotFoundException No event found.
     */
    private boolean alreadyRegistered(String eventId, String userId) throws EventNotFoundException {
        Optional<Event> event = getEventById(eventId);
        if (!event.isPresent())
            throw new EventNotFoundException("Incorrect event id");
        for (String registeredId : event.get().getUserIds()) {
            if (registeredId.equals(userId)) return true;
        }
        return false;
    }

    /**
     * Add a user id to the userId list of a event. (when register a user to an event.)
     * @param eventId The id of the event.
     * @param userId The id of the user.
     * @return True if the user id is added to the userId list of the event. Return false otherwise (user already
     * registered to this event or user limit is reached).
     * @throws EventNotFoundException No event found.
     */
    public boolean addUserIdToEventUserIdList(String eventId, String userId) throws EventNotFoundException {
        Optional<Event> optionalEvent = getEventById(eventId);
        if (!optionalEvent.isPresent())
            throw new EventNotFoundException("Incorrect event id");
        Event event = optionalEvent.get();
        if (alreadyRegistered(event.getId(), userId) || getUserCountById(event.getId()) >= event.getUserLimit()) return false;
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
        Optional<Event> optionalEvent = getEventById(eventId);
        if (!optionalEvent.isPresent())
            throw new EventNotFoundException("Incorrect event id");
        Event event = optionalEvent.get();
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
    public void registerSpeakerForEvent(String speakerId, String eventId) throws CommandException {
        for (Event event : eventPersistencePort.getAllEvents()){
            if (event.getId().equals(eventId)) {
                event.addSpeakerId(speakerId);
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
