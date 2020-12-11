package group0153.conferencesystem.application.room;

import group0153.conferencesystem.application.room.data.RoomData;
import group0153.conferencesystem.entities.room.Room;
import group0153.conferencesystem.exceptions.RoomNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RoomManager {
    RoomPersistencePort roomPersistencePort;

    /**
     * Construct an instance of RoomManager.
     * @param roomPersistencePort How the rooms are saved to the database.
     */
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

    /**
     * Returns a room based on the id.
     *
     * @param roomId Id of the room to find.
     * @return The room based on the id.
     * @throws RoomNotFoundException Thrown when the room id is not valid.
     */
    public RoomData getRoomById(String roomId) throws RoomNotFoundException {
        Optional<Room> roomOptional = roomPersistencePort.findById(roomId);
        if (!roomOptional.isPresent())
            throw new RoomNotFoundException(roomId);
        Room room = roomOptional.get();
        return new RoomData(room.getName(), room.getCapacity());
    }
}
