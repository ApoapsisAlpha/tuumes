package group0153.conferencesystem.adapters.gateways.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<EventModel, Long>{
    Optional<EventModel> findById(String eventId);
}
