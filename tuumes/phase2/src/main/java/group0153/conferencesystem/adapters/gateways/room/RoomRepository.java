package group0153.conferencesystem.adapters.gateways.room;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Room interface for database operations on rooms
 */
public interface RoomRepository extends JpaRepository<RoomModel, Long> {

}
