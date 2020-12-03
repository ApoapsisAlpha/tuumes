package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.Event;

import java.util.ArrayList;


/**
 * Handles the scheduling of events.
 */
public class EventScheduler {
    private final ArrayList<Event> events;

    public EventScheduler(ArrayList<Event> events) {
        this.events = events;
    }
}
