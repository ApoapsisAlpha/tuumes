package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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
     * Return an event name based on its id.
     * @param eventId The id of the event.
     * @return The name of the event if the event exists, otherwise returns null.
     */
    public String getEventNameById(String eventId) {
        Optional<Event> requestedEvent = eventPersistencePort.findById(eventId);
        return requestedEvent.map(Event::getEventName).orElse(null);
    }

    /**
     * Return an event description based on its id.
     * @param eventId The id of the event.
     * @return The description of the event if the event exists, otherwise returns null.
     */
    public String getDescriptionById(String eventId) {
        Optional<Event> requestedEvent = eventPersistencePort.findById(eventId);
        return requestedEvent.map(Event::getDescription).orElse(null);
    }

    /**
     * Return an event's start time based on its id.
     * @param eventId The id of the event.
     * @return The start time of the event if the event exists, otherwise returns null.
     */
    public Date getStartTimeById(String eventId){
        Optional<Event> requestedEvent = eventPersistencePort.findById(eventId);
        return requestedEvent.map(Event::getStartTime).orElse(null);
    }

    /**
     * Return an event's end time based on its id.
     * @param eventId The id of the event.
     * @return The end time of the event if the event exists, otherwise returns null.
     */
    public Date getEndTimeById(String eventId){
        Optional<Event> requestedEvent = eventPersistencePort.findById(eventId);
        return requestedEvent.map(Event::getEndTime).orElse(null);
    }

    /**
     * Return an event's room id based on its id.
     * @param eventId The id of the event.
     * @return The room id of the event if the event exists, otherwise returns null.
     */
    public String getRoomIdByEventId(String eventId){
        Optional<Event> requestedEvent = eventPersistencePort.findById(eventId);
        return requestedEvent.map(Event::getRoomId).orElse(null);
    }

    /**
     * Return an event's user limit based on its id.
     * @param eventId The id of the event.
     * @return The user limit of the event if the event exists, otherwise returns -1.
     */
    public int getUserLimitById(String eventId){
        Optional<Event> requestedEvent = eventPersistencePort.findById(eventId);
        return requestedEvent.map(Event::getUserLimit).orElse(-1);
    }

    /**
     * Return an event's user count based on its id.
     * @param eventId The id of the event.
     * @return The user count of the event if the event exists, otherwise returns -1.
     */
    public int getUserCountById(String eventId){
        Optional<Event> requestedEvent = eventPersistencePort.findById(eventId);
        return requestedEvent.map(Event::getUserCount).orElse(-1);
    }

    /**
     * Return a list of user ids of the event based on given event id.
     * @param eventId The id of the event.
     * @return The the list of user ids of the event if the event exists, otherwise returns null.
     */
    public ArrayList<String> getUserIdsByEventId(String eventId){
        Optional<Event> requestedEvent = eventPersistencePort.findById(eventId);
        return requestedEvent.map(Event::getUserIds).orElse(null);
    }

    /**
     * Return whether the event based on given event id.
     * @param eventId The id of the event.
     * @return True if the event exists and is a VIP-only event, otherwise returns false.
     */
    public boolean getIsVipOnlyEventById(String eventId){
        Optional<Event> requestedEvent = eventPersistencePort.findById(eventId);
        return requestedEvent.map(Event::isVipOnlyEvent).orElse(false);
    }
}
