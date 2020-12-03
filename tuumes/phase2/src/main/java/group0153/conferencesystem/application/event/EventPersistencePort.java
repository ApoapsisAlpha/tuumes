package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;

public interface EventPersistencePort {

    void saveEvent(Event event);

}
