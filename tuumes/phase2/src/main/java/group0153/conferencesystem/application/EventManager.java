package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.Event;
import group0153.conferencesystem.entities.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Component
public class EventManager {

    EventPersistencePort eventPersistencePort;

    public EventManager(EventPersistencePort eventPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     * Create an event with the given description, start time, end time, room, list of speakers' id and user limit.
     * @param description the description of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param room the room of the event
     * @param speakerIds the list of speakers' id of the event
     * @param userLimit the user limit of the event
     * @return the id of the created event
     */
    public String createEvent(String description, Date startTime, Date endTime, Room room, ArrayList<String> speakerIds, int userLimit){
        String id = UUID.randomUUID().toString();
        Event event = new Event(id, description, startTime, endTime, room, speakerIds, userLimit);
        eventPersistencePort.saveEvent(event);
        return id;
    }
}