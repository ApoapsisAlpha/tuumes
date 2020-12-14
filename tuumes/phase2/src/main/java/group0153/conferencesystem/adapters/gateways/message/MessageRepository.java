package group0153.conferencesystem.adapters.gateways.message;

import group0153.conferencesystem.adapters.gateways.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Message interface for database operations on messages
 */
public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    /**
     * Get the MessageModel instance within an Optional corresponding to the id specified if it exists
     *
     * @param msgId id of the message that is requested
     * @return Optional possibly containing an instance of MessageModel if it exists
     */
    Optional<MessageModel> findByResourceId(String msgId);

    /**
     * Get the messages from sender.
     *
     * @param sender the UserModel of the user that sent the messages
     * @return list of messages
     */
    List<MessageModel> findAllBySender(UserModel sender);
}
