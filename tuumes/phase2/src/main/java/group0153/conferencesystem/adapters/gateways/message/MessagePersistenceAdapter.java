package group0153.conferencesystem.adapters.gateways.message;

import group0153.conferencesystem.adapters.gateways.user.UserModel;
import group0153.conferencesystem.adapters.gateways.user.UserRepository;
import group0153.conferencesystem.application.message.MessagePersistencePort;
import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A class facilitating the retrieval and saving of information pertaining to messages to/from the database.
 */
public class MessagePersistenceAdapter implements MessagePersistencePort {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    /**
     * Construct an instance of MessagePersistenceAdapter using the provided MessageRepository instance.
     *
     * @param messageRepository instance of MessageRepository that can facilitate message saving and retrieval
     */
    public MessagePersistenceAdapter(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /**
     * Given a Message, save it to the database
     *
     * @param message The Message to be saved
     */
    @Override
    public void saveMessage(Message message) {
        Set<UserModel> recipients = message.getRecipientIds().stream().map(recipientId -> {
            return userRepository.findByResourceId(recipientId).get();
        }).collect(Collectors.toSet());
        UserModel sender = userRepository.findByResourceId(message.getSenderId()).get();
        MessageModel messageModel = new MessageModel(message.getId(), message.getMessageContent(), sender, recipients);

        messageRepository.save(messageModel);
    }

    /**
     * Given a Message that already exists, update the database Model to the given Message's read status
     *
     * @param message The message to be updated
     * @param userId  The user to update
     * @param status  The new status
     */
    @Override
    public void updateMessageReadStatus(Message message, String userId, boolean status) {

    }

    /**
     * Given a Message that already exists, update the database Model to the given Message's archived status
     *
     * @param message The message to be updated
     * @param userId  The user to update
     * @param status  The new status
     */
    @Override
    public void updateMessageArchivedStatus(Message message, String userId, boolean status) {

    }

    /**
     * Given a Message that already exists, update the database Model to the given Message's archived status
     *
     * @param message The message to be updated
     * @param userId  The user to update
     * @param status  The new status
     */
    @Override
    public void updateMessageDeletedStatus(Message message, String userId, boolean status) {

    }

    /**
     * Find an optional Message by id
     *
     * @param msgId The id of the message being found
     * @return Returns an Optional Message with the given id
     */
    @Override
    public Optional<Message> findById(String msgId) {
        return Optional.empty();
    }

    /**
     * Given a user, returns a list of all Messages (archived and unarchived) that were sent to a user
     *
     * @param user The id of the user whose Messages are being given
     * @return A List of all Messages of the given user
     */
    @Override
    public ArrayList<Message> getMsgsToUser(String user) {
        return null;
    }

    /**
     * Given a senderid, returns a list of all Message ids sent by that sender
     *
     * @param sender The id of the sender whose messages are being found
     * @return A List of all messages sent by the given sender
     */
    @Override
    public ArrayList<String> getMsgIdsBySender(String sender) {
        return null;
    }
}
