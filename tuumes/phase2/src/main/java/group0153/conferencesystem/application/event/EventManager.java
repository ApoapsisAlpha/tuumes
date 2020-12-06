package group0153.conferencesystem.application.event;

import group0153.conferencesystem.application.event.exception.EventNotFoundException;
import group0153.conferencesystem.application.user.exception.IncorrectLoginException;
import group0153.conferencesystem.entities.event.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EventManager {
    EventPersistencePort eventPersistencePort;

    public EventManager(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

//    /**
//     * Create an event with the given description, start time, end time, room, list of speakers' id and user limit.
//     * @param description the description of the event
//     * @param startTime the start time of the event
//     * @param endTime the end time of the event
//     * @param room the room of the event
//     * @param speakerIds the list of speakers' id of the event
//     * @param userLimit the user limit of the event
//     * @return the id of the created event
//     */
//    public String createEvent(String eventName,
//                              String description,
//                              Date startTime,
//                              Date endTime,
//                              String room,
//                              ArrayList<String> speakerIds,
//                              int userLimit,
//                              boolean isVipOnlyEvent){
//        String id = UUID.randomUUID().toString();
//        Event event = new Event(eventName, id, description, startTime, endTime, room, userLimit, isVipOnlyEvent);
//        eventPersistencePort.saveEvent(event);
//        return id;
//    }

    //TODO: more event manager methods here

    // Below are the getters of each instance of event given an event id
    // for schedule display
    // instead of returning entity Event from getEventById(String eventId)

    /**
     * Private method. Return an event based on its id.
     * @param eventId The id of the event.
     * @return A reference to the event if the event exists, otherwise returns null
     */
    private Optional<Event> getEventById(String eventId){
        return eventPersistencePort.findById(eventId);
    }

    /**
     * Return an event name based on its id.
     * @param eventId The id of the event.
     * @return The name of the event if the event exists, otherwise returns null.
     */
    public String getEventNameById(String eventId) {
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::getEventName).orElse(null);
    }

    /**
     * Return an event description based on its id.
     * @param eventId The id of the event.
     * @return The description of the event if the event exists, otherwise returns null.
     */
    public String getDescriptionById(String eventId) {
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::getDescription).orElse(null);
    }

    /**
     * Return an event's start time based on its id.
     * @param eventId The id of the event.
     * @return The start time of the event if the event exists, otherwise returns null.
     */
    public Date getStartTimeById(String eventId){
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::getStartTime).orElse(null);
    }

    /**
     * Return an event's end time based on its id.
     * @param eventId The id of the event.
     * @return The end time of the event if the event exists, otherwise returns null.
     */
    public Date getEndTimeById(String eventId){
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::getEndTime).orElse(null);
    }

    /**
     * Return an event's room id based on its id.
     * @param eventId The id of the event.
     * @return The room id of the event if the event exists, otherwise returns null.
     */
    public String getRoomIdByEventId(String eventId){
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::getRoomId).orElse(null);
    }

    /**
     * Return an event's user limit based on its id.
     * @param eventId The id of the event.
     * @return The user limit of the event if the event exists, otherwise returns -1.
     */
    public int getUserLimitById(String eventId){
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::getUserLimit).orElse(-1);
    }

    /**
     * Return an event's user count based on its id.
     * @param eventId The id of the event.
     * @return The user count of the event if the event exists, otherwise returns -1.
     */
    public int getUserCountById(String eventId){
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::getUserCount).orElse(-1);
    }

    /**
     * Return a list of user ids of the event based on given event id.
     * @param eventId The id of the event.
     * @return The the list of user ids of the event if the event exists, otherwise returns null.
     */
    public ArrayList<String> getUserIdsByEventId(String eventId){
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::getUserIds).orElse(null);
    }

    /**
     * Return whether the event based on given event id.
     * @param eventId The id of the event.
     * @return True if the event exists and is a VIP-only event, otherwise returns false.
     */
    public boolean getIsVipOnlyEventById(String eventId){
        Optional<Event> requestedEvent = getEventById(eventId);
        return requestedEvent.map(Event::isVipOnlyEvent).orElse(false);
    }

    // below are methods other than the getters.

    /**
     * Returns all active events. (Ignores events that have already passed)
     * @return Arraylist of events.
     */
    public ArrayList<String> getActiveEventIds() {
        Date current = new Date();
        ArrayList<String> result = new ArrayList<String>();
        for (Event event : eventPersistencePort.getAllEvents()){
            if (!event.getStartTime().before(current)){
                result.add(event.getId());
            }
        }
        return result;
    }

    /**
     * Check if two events collide with each other. Two events collides with each other if both events using the same
     * room and have time conflict.
     * @param eventId1 event id of the first event.
     * @param eventId2 event id of the second event.
     * @return True if the first event collides with the second event.
     */
    public boolean collidesWith(String eventId1, String eventId2){
        Optional<Event> event1 = getEventById(eventId1);
        Optional<Event> event2 = getEventById(eventId2);
        if (event1.isPresent() && event2.isPresent()){
            if (!event1.get().getRoomId().equals(event2.get().getRoomId())){
                return false;
            }
        }
        return haveTimeConflict(eventId1, eventId2);
    }

    /**
     * Chech if two events have time conflict.
     * @param eventId1 event id of the first event.
     * @param eventId2 event id of the second event.
     * @return True if the first event and the second event have time conflict.
     */
    private boolean haveTimeConflict(String eventId1, String eventId2) {
        Optional<Event> event1 = getEventById(eventId1);
        Optional<Event> event2 = getEventById(eventId2);
        if (!event1.isPresent() || !event2.isPresent()){
            return false;
        }
        if (event1.get().getStartTime().equals(event2.get().getStartTime()))
            return true;
        if (event1.get().getStartTime().before(event2.get().getStartTime())) {
            return event1.get().getEndTime().after(event2.get().getStartTime());
        }
        return event1.get().getStartTime().before(event2.get().getEndTime());
    }

    /**
     * Tries to add an event by time.
     * @param event: The event to be added.
     * @return Returns true if the event has been successfully added by time. Returns false otherwise.
     */
    public boolean addEvent(Event event) {
        ArrayList<String> eventIds = getActiveEventIds();
        for (String otherEventId : eventIds) {
            if (collidesWith(event.getId(), otherEventId))
                return false;
        }
        int idx = -1;
        for (int i = 0; i < eventIds.size(); ++i){
            if (event.getStartTime().before(getEventById(eventIds.get(i)).get().getStartTime())){
                idx = i;
            }
        }
        if (idx == -1)
            eventIds.add(event.getId());
        else
            eventIds.add(idx, event.getId());
        return true;
    }

    /**
     * Tries to remove the event by its id (if an event with this id exists).
     * @param eventId The id of the event to be removed.
     * @return Returns true if the event has been removed. Returns false if their was no event with the given id.
     */
    public boolean removeEventById(String eventId) {
        Optional<Event> event = getEventById(eventId);
        ArrayList<String> eventIds = getActiveEventIds();
        if (!event.isPresent()) return false;
        for (String otherId : eventIds) {
            if (event.get().getId().equals(otherId)) {
                eventIds.remove(eventId);
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the given user has already registered to the given event.
     * @param eventId The event id of the event.
     * @param userId The user id of the user.
     * @return True if the user id has been remove from the userId list of the event, false if the user Id is not in the list.
     * @throws EventNotFoundException No event found.
     */
    public boolean alreadyRegistered(String eventId, String userId) throws EventNotFoundException {
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
}
