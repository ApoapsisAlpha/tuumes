package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.Event;
import group0153.conferencesystem.entities.MultiSpeakerEvent;
import group0153.conferencesystem.entities.NoSpeakerEvent;
import group0153.conferencesystem.entities.OneSpeakerEvent;

/**
 * An event factory that returns event instances (instances of subclasses of event).
 */
public class EventFactory {
    /**
     *
     * @param event The type of the event to be created (non-case sensitive).
     *              Options are:
     *              MultiSpeakerEvent
     *              NoSpeakerEvent
     *              OneSpeakerEvent
     * @return Returns an instance of the event that has been requested. Variables of this instance will have to be set manually.
     * If the event requested does not exist, returns null.
     */
    public Event createEvent(String event) {
        if (event.equalsIgnoreCase("MultiSpeakerEvent")) return new MultiSpeakerEvent();
        if (event.equalsIgnoreCase("NoSpeakerEvent")) return new NoSpeakerEvent();
        if (event.equalsIgnoreCase("OneSpeakerEvent")) return new OneSpeakerEvent();
        return null;
    }
}
