package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;

import java.util.ArrayList;


/**
 * Handles the scheduling of events.
 */
public class EventScheduler {
    private final ArrayList<Event> events;

    /**
     *
     * @param events The list of events that are current scheduled.
     */
    public EventScheduler(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     *
     * @param event The event to be scheduled.
     * @return "Time conflict" if there is a time conflict (the cannot be scheduled successfully). "Success" otherwise.
     */
    public String scheduleEvent(Event event) {
        for (Event otherEvent : this.events) {
            if (timeConflict(event, otherEvent)) {
                return "Time conflict.";
            }
        }
        this.events.add(event);
        return "Success.";
    }

    /**
     *
     * @param e1 First event.
     * @param e2 Second event.
     * @return True if they have a time conflict. False otherwise.
     */
    private boolean timeConflict(Event e1, Event e2) {
        if (e1.getRoomId().equals(e2.getRoomId())) {
            if (e1.getStartTime().equals(e2.getStartTime()))
                return true;
            if (e1.getStartTime().before(e2.getStartTime())) {
                return e1.getEndTime().after(e2.getStartTime());
            }
            return e1.getStartTime().before(e2.getEndTime());
        }
        return false;
    }
}
