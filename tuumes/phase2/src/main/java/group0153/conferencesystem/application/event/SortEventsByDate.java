package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;

import java.util.Comparator;

/**
 * A event use case class that handles the sorting of events by time.
 */
public class SortEventsByDate implements Comparator<Event> {

    /**
     * Compare two events based on their start time.
     * @param e1 First event to be compared.
     * @param e2 Second event to be compared.
     * @return Return -1 if the first event starts earlier than the second event, 1 if the second event stars earlier
     *         than the first event, 0 if both events start at the same time.
     */
    public int compare(Event e1, Event e2) {
        if (e1.getStartTime().isBefore(e2.getStartTime())) return -1;
        else if (e1.getStartTime().isAfter(e2.getStartTime())) return 1;
        else return 0;
    }
}
