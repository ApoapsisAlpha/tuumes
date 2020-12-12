package group0153.conferencesystem.adapters.gateways.message;

import group0153.conferencesystem.adapters.gateways.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
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
     * Get all messages from recipient.
     *
     * @param recipientId recipient id
     * @return list of message models
     */
    @Query(value = "select * from message_model where resource_id in (select message_model_id in message_model_recipients where recipients_id=?1)", nativeQuery = true)
    List<MessageModel> findAllByRecipientId(String recipientId);

    /**
     * Get the messages from sender.
     *
     * @param sender
     * @return list of messages
     */
    List<MessageModel> findAllBySender(UserModel sender);
}
