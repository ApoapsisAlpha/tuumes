package group0153.conferencesystem.adapters.gateways.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Message interface for database operations on messages
 */
public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    Optional<MessageModel> findById(String msgId);
}
