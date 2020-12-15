package group0153.conferencesystem.application.event;

import group0153.conferencesystem.application.exceptions.EventNotFoundException;
import group0153.conferencesystem.application.exceptions.FullRoomException;
import group0153.conferencesystem.application.room.RoomPersistencePort;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.entities.room.Room;
import org.springframework.stereotype.Component;

/**
 * Manager responsibly for updating events
 */
@Component
public class EventUpdateManager {

    private final EventPersistencePort eventPersistencePort;
    private final RoomPersistencePort roomPersistencePort;

    /**
     * Creates an instance of the manager.
     * @param eventPersistencePort an event persistence port instance
     * @param roomPersistencePort a room persistence port instance
     */
    public EventUpdateManager(EventPersistencePort eventPersistencePort, RoomPersistencePort roomPersistencePort) {
        this.eventPersistencePort = eventPersistencePort;
        this.roomPersistencePort = roomPersistencePort;
    }

    /**
     * Update the event capacity
     * @param eventId the event id
     * @param newCapacity the new capacity
     * @throws EventNotFoundException thrown if event is not found
     * @throws FullRoomException thrown if the new capacity is greater than the room capcity
     */
    public void updateCapacity(String eventId, int newCapacity) throws EventNotFoundException, FullRoomException {
        Event event = eventPersistencePort.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        Room room = roomPersistencePort.findById(event.getRoomId()).get();
        if (newCapacity > room.getCapacity())
            throw new FullRoomException(room.getId());

        eventPersistencePort.updateEventCapacity(event.getId(), newCapacity);
    }

}
