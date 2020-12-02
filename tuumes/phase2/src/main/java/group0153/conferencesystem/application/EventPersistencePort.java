package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.Event;

public interface EventPersistencePort {

    void saveEvent(Event event);

}
