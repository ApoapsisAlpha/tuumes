package group0153.conferencesystem.adapters.gateways.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Room interface for database operations on rooms
 */
public interface RoomRepository extends JpaRepository<RoomModel, Long> {

    Optional<RoomModel> findByResourceId(String roomId);
}
