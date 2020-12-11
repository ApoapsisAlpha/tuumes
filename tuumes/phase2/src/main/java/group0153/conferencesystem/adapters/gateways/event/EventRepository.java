package group0153.conferencesystem.adapters.gateways.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Event interface for database operations on events
 */
public interface EventRepository extends JpaRepository<EventModel, Long> {
    /**
     * Get the EventModel instance within an Optional corresponding to the id specified if it exists
     *
     * @param resourceId String database id of the message that is requested
     * @return Optional possibly containing an instance of EventModel if it exists
     */
    Optional<EventModel> findByResourceId(String resourceId);

}
