package group0153.conferencesystem.adapters.gateways.room;

import group0153.conferencesystem.application.room.RoomPersistencePort;
import group0153.conferencesystem.entities.room.Room;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adapter class that adapts RoomRepository to work with database storage
 */
@Component
public class RoomPersistenceAdapter implements RoomPersistencePort {

    private final RoomRepository roomRepository;

    /**
     * Constructs an instance of RoomPersistenceAdapter using the provided RoomRepository instance
     *
     * @param roomRepository a repository storing Rooms
     */
    public RoomPersistenceAdapter(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Saves the room to the database.
     *
     * @param room Room object to be saved.
     */
    @Override
    public void saveRoom(Room room) {
        RoomModel roomModel = new RoomModel(room.getId(), room.getName(), room.getCapacity());
        roomRepository.save(roomModel);
    }

    /**
     * Find a room based on their id.
     *
     * @param roomId The rooms id.
     * @return The room with the id. (Empty if they don't exist)
     */
    @Override
    public Optional<Room> findById(String roomId) {
        return Optional.empty();
    }
}
