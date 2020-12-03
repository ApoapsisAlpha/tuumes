package group0153.conferencesystem.application;
import group0153.conferencesystem.entities.Event;

import java.util.ArrayList;

/**
 * Handles the registration of users for events (from the events perspective).
 */
public class EventRegistry {
    private final ArrayList<Event> events;

    public EventRegistry(ArrayList<Event> events) {
        this.events = events;
    }
}
