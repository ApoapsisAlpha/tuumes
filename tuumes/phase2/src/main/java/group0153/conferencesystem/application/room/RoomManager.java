package group0153.conferencesystem.application.room;

import group0153.conferencesystem.application.room.data.RoomData;
import group0153.conferencesystem.entities.room.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A manager class that manipulates Room entities
 */
@Component
public class RoomManager {
    final RoomPersistencePort roomPersistencePort;

    /**
     * Construct an instance of RoomManager.
     *
     * @param roomPersistencePort How the rooms are saved to the database.
     */
    public RoomManager(RoomPersistencePort roomPersistencePort) {
        this.roomPersistencePort = roomPersistencePort;
    }

    /**
     * Create a room with the given name and capacity.
     *
     * @param name     the name of the room
     * @param capacity the capacity of the room
     */
    public void createRoom(String name, int capacity) {
        String id = UUID.randomUUID().toString();
        Room room = new Room(id, name, capacity);
        roomPersistencePort.saveRoom(room);
    }

//    /**
//     * Returns a room based on the id.
//     *
//     * @param roomId Id of the room to find.
//     * @return The room based on the id.
//     * @throws RoomNotFoundException Thrown when the room id is not valid.
//     */
//    public RoomData getRoomById(String roomId) throws RoomNotFoundException {
//        Optional<Room> roomOptional = roomPersistencePort.findById(roomId);
//        if (!roomOptional.isPresent())
//            throw new RoomNotFoundException(roomId);
//        Room room = roomOptional.get();
//        return new RoomData(room.getName(), room.getCapacity());
//    }

    /**
     * Returns a list of every room within the conference.
     *
     * @return List of rooms.
     */
    public List<RoomData> getRooms() {
        List<Room> rooms = roomPersistencePort.getAllRooms();
        List<RoomData> roomsData = new ArrayList<>();
        for (Room room : rooms) {
            roomsData.add(new RoomData(room.getId(), room.getName(), room.getCapacity()));
        }

        return roomsData;
    }
}
