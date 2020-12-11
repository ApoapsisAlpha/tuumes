package group0153.conferencesystem.application.room;

import group0153.conferencesystem.entities.room.Room;

import java.util.Optional;

/**
 * Adapter class that adapts room storage to work with database storage
 */
public interface RoomPersistencePort {
    /**
     * Saves the room.
     *
     * @param room Room object to be saved.
     */
    void saveRoom(Room room);

    /**
     * Find a room based on their id.
     *
     * @param roomId The rooms id.
     * @return The room with the id. (Empty if they don't exist)
     */
    Optional<Room> findById(String roomId);
}
