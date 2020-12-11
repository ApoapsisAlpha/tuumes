package group0153.conferencesystem.adapters.gateways.room;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Room interface for database operations on rooms
 */
public interface RoomRepository extends JpaRepository<RoomModel, Long> {

    /**
     * Get an instance of RoomModel that is specified by the database id roomId
     *
     * @param roomId the id corresponding to a room in the database
     * @return RoomModel instance corresponding to the id provided
     */
    RoomModel findByResourceId(String roomId);
}
