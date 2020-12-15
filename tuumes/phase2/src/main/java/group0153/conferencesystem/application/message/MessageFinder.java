package group0153.conferencesystem.application.message;

import group0153.conferencesystem.application.exceptions.UserNotFoundException;
import group0153.conferencesystem.application.message.data.MessageData;
import group0153.conferencesystem.application.user.UserPersistencePort;
import group0153.conferencesystem.entities.message.Message;
import group0153.conferencesystem.entities.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A message use case class to find different messages depending on archived state,
 * sender, recipient, or read state.
 */

@Component
public class MessageFinder {
    private final MessagePersistencePort messagePersistencePort;
    private final UserPersistencePort userPersistencePort;

    /**
     * Instantiates a MessageManager.
     *
     * @param messagePersistencePort a message persistence port.
     * @param userPersistencePort a user persistence port.
     */
    public MessageFinder(MessagePersistencePort messagePersistencePort, UserPersistencePort userPersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    /**
     * Gets all unarchived messages for the given user
     *
     * @param userId user id
     * @return list of message data.
     * @throws UserNotFoundException thrown if the user does not exists
     */
    public List<MessageData> getMessages(String userId) throws UserNotFoundException {
        userPersistencePort.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Message> messages = messagePersistencePort.getMessages(userId);
        List<Message> filteredMessages = messages.stream().filter(m -> !m.isArchived(userId))
                                                          .filter(m -> !m.isDeleted(userId))
                                                          .collect(Collectors.toList());

        return filteredMessages.stream().map(message -> {
            User sender = userPersistencePort.findById(message.getSenderId()).get();
            return new MessageData(message, sender, userId);
        }).collect(Collectors.toList());
    }

    /**
     * Gets all archived messages for the given user
     *
     * @param userId user id
     * @return list of message data.
     * @throws UserNotFoundException thrown if the user does not exists
     */
    public List<MessageData> getArchivedMessages(String userId) {
        userPersistencePort.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Message> messages = messagePersistencePort.getMessages(userId);
        List<Message> filteredMessages = messages.stream().filter(m -> !m.isDeleted(userId))
                                                          .collect(Collectors.toList());

        return filteredMessages.stream().map(message -> {
            User sender = userPersistencePort.findById(message.getSenderId()).get();
            return new MessageData(message, sender, userId);
        }).collect(Collectors.toList());
    }
}