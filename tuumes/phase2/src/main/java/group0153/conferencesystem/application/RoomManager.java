package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.Room;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoomManager {

    RoomPersistencePort roomPersistencePort;

    public RoomManager(RoomPersistencePort roomPersistencePort) {
        this.roomPersistencePort = roomPersistencePort;
    }

    /**
     * Create a room with the given name and capacity.
     *
     * @param name the name of the room
     * @param capacity the capacity of the room
     * @return the id of the created room
     */
    public String createRoom(String name, int capacity) {
        String id = UUID.randomUUID().toString();
        Room room = new Room(id, name, capacity);
        roomPersistencePort.saveRoom(room);
        return id;
    }

}
