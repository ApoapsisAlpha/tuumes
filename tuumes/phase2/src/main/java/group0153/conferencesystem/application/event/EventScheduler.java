package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.CommandException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


/**
 * Handles the scheduling of events.
 */
public class EventScheduler {
    EventPersistencePort eventPersistencePort;


//    /**
//     *
//     * @param events The list of events that are current scheduled.
//     */
//    public EventScheduler(ArrayList<Event> events) {
//        this.events = events;
//    }
    public EventScheduler(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     * Returns all active events. (Ignores events that have already passed)
     * @return Arraylist of events.
     */
    private ArrayList<String> getActiveEventIds() {
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
     *
     * @param events The list of events that are current scheduled.
     */
    public EventScheduler(ArrayList<Event> events) {
        this.events = events;
    }

//    /**
//     *
//     * @param event The event to be scheduled.
//     * @throws CommandException The event could not be added successfully.
//     */
//    public void scheduleEvent(Event event) throws CommandException {
//        for (Event otherEvent : eventPersistencePort.getAllEvents()) {
//            if (timeConflict(event, otherEvent)) {
//                throw new CommandException("The event to be scheduled has a time conflict.");
//            }
//        }
//        this.events.add(event);
//    }

    /**
     * Tries to add an event to the schedule by time.
     * @param event: The event to be added.
     * @throws CommandException The event could not be added successfully.
    */
    public void scheduleEvent(Event event) throws CommandException {
        ArrayList<String> eventIds = getActiveEventIds();
        for (String otherEventId : eventIds) {
            if (collidesWith(event.getId(), otherEventId)){
                throw new CommandException("The event to be scheduled has a time conflict.");
            }
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
    }

    /**
     * Check if two events collide with each other. Two events collides with each other if both events using the same
     * room and have time conflict.
     * @param eventId1 event id of the first event.
     * @param eventId2 event id of the second event.
     * @return True if the first event collides with the second event.
     */
    private boolean collidesWith(String eventId1, String eventId2){
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

//    /**
//     *
//     * @param e1 First event.
//     * @param e2 Second event.
//     * @return True if they have a time conflict. False otherwise.
//     */
//    private boolean timeConflict(Event e1, Event e2) {
//        if (e1.getRoomId().equals(e2.getRoomId())) {
//            if (e1.getStartTime().equals(e2.getStartTime()))
//                return true;
//            if (e1.getStartTime().before(e2.getStartTime())) {
//                return e1.getEndTime().after(e2.getStartTime());
//            }
//            return e1.getStartTime().before(e2.getEndTime());
//        }
//        return false;
//    }

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
            return e1.getStartTime().before(e2.getEndTime());
        }
        return false;
    }
}
