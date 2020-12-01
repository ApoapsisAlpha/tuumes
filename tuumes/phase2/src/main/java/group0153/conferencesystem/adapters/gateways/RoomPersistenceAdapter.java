package group0153.conferencesystem.adapters.gateways;

import group0153.conferencesystem.application.RoomPersistencePort;
import group0153.conferencesystem.entities.Room;
import org.springframework.stereotype.Component;

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
}
