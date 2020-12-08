package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;

import java.util.Comparator;

public class SortEventsByDate implements Comparator<Event> {
    public int compare(Event e1, Event e2) {
        if (e1.getStartTime().before(e2.getStartTime())) return -1;
        else if (e1.getStartTime().after(e2.getStartTime())) return 1;
        else return 0;
    }
}
