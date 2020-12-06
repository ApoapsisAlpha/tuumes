package group0153.conferencesystem.application.message;

import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;

public class MessageCreationManager {
    private MessagePersistencePort messagePersistencePort;

    /**
     * Constructor to instantiate a MessageCreationManager using message repository
     * @param messagePersistencePort where messages are stored
     */
    public MessageCreationManager(MessagePersistencePort messagePersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
    }

    /**
     * Create a message with the provided parameters and return its id.
     *
     * @param messageContent: String with the message's content
     * @param senderId: id of sender
     * @param recipientIds: an ArrayList of the recipient(s)'s id(s)
     * @return the id of the message created
     */
    public String create(String messageContent, String senderId, ArrayList<String> recipientIds) {
        Message message = new Message(messageContent, senderId, recipientIds);
        messageRepository.add(message);
        return message.getId();
    }
}
