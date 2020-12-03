package group0153.conferencesystem.application.room;

import group0153.conferencesystem.entities.room.Room;

public interface RoomPersistencePort {
    void saveRoom(Room room);
}
