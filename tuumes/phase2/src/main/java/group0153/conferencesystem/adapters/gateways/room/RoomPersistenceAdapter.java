package group0153.conferencesystem.adapters.gateways.room;

import group0153.conferencesystem.application.room.RoomPersistencePort;
import group0153.conferencesystem.entities.room.Room;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoomPersistenceAdapter implements RoomPersistencePort {

    private RoomRepository roomRepository;

    public RoomPersistenceAdapter(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

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
