package group0153.conferencesystem.adapters.gateways.message;

import group0153.conferencesystem.adapters.gateways.user.UserModel;
import group0153.conferencesystem.adapters.gateways.user.UserRepository;
import group0153.conferencesystem.application.message.MessagePersistencePort;
import group0153.conferencesystem.entities.message.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A class facilitating the retrieval and saving of information pertaining to messages to/from the database.
 */
@Component
public class MessagePersistenceAdapter implements MessagePersistencePort {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    /**
     * Construct an instance of MessagePersistenceAdapter using the provided MessageRepository and UserRepository
     * instances.
     *
     * @param messageRepository instance of MessageRepository that can facilitate message saving and retrieval
     * @param userRepository    instance of UserRepository that can facilitate user information retrieval
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
        Set<UserModel> recipients = message.getRecipientIds().stream().map(recipientId -> userRepository.findByResourceId(recipientId).get()).collect(Collectors.toSet());
        UserModel sender = userRepository.findByResourceId(message.getSenderId()).get();
        MessageModel messageModel = new MessageModel(message.getId(), message.getMessageContent(), sender, recipients);
        for (UserModel recipient : recipients) {
            recipient.getMessages().add(messageModel);
        }

        messageRepository.save(messageModel);
    }

    /**
     * Given a Message that already exists, update the database Model to the given Message's read status
     *
     * @param messageId The message id of the message to be updated
     * @param userId    The user to update
     * @param status    The new status
     */
    @Override
    public void updateMessageReadStatus(String messageId, String userId, boolean status) {
        MessageModel messageModel = messageRepository.findByResourceId(messageId).get();
        UserModel userModel = userRepository.findByResourceId(userId).get();
        if (status) {
            messageModel.getReadSet().add(userModel);
        } else {
            messageModel.getReadSet().remove(userModel);
        }

        messageRepository.flush();
    }

    /**
     * Given a Message that already exists, update the database Model to the given Message's archived status
     *
     * @param messageId The message id of the message to be updated
     * @param userId    The user to update
     * @param status    The new status
     */
    @Override
    public void updateMessageArchivedStatus(String messageId, String userId, boolean status) {
        MessageModel messageModel = messageRepository.findByResourceId(messageId).get();
        UserModel userModel = userRepository.findByResourceId(userId).get();
        if (status) {
            messageModel.getArchivedSet().add(userModel);
        } else {
            messageModel.getArchivedSet().remove(userModel);
        }

        messageRepository.flush();
    }

    /**
     * Given a Message that already exists, update the database Model to the given Message's archived status
     *
     * @param messageId The message id of the message to be updated
     * @param userId    The user to update
     * @param status    The new status
     */
    @Override
    public void updateMessageDeletedStatus(String messageId, String userId, boolean status) {
        MessageModel messageModel = messageRepository.findByResourceId(messageId).get();
        UserModel userModel = userRepository.findByResourceId(userId).get();
        if (status) {
            messageModel.getDeleteSet().add(userModel);
        } else {
            messageModel.getDeleteSet().remove(userModel);
        }

        messageRepository.flush();
    }

    /**
     * Find an optional Message by id
     *
     * @param msgId The id of the message being found
     * @return Returns an Optional Message with the given id
     */
    @Override
    public Optional<Message> findById(String msgId) {
        Optional<MessageModel> messageModel = messageRepository.findByResourceId(msgId);
        return new MessageMapper().mapModelToEntity(messageModel);
    }

    /**
     * Given a user, returns a list of all Messages (archived and unarchived) that were sent to a user
     *
     * @param userId The id of the user whose Messages are being given
     * @return A List of all Messages of the given user
     */
    @Override
    public List<Message> getMessages(String userId) {
        UserModel userModel = userRepository.findByResourceId(userId).get();
        Set<MessageModel> messageModels = userModel.getMessages();
        MessageMapper mapper = new MessageMapper();
        return messageModels.stream().map(m -> mapper.mapModelToEntity(Optional.of(m)).get()).collect(Collectors.toList());
    }
}
