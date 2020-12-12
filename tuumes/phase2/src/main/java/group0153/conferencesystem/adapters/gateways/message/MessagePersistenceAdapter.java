package group0153.conferencesystem.adapters.gateways.message;

import group0153.conferencesystem.application.message.MessagePersistencePort;
import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;
import java.util.Optional;

public class MessagePersistenceAdapter implements MessagePersistencePort {

    private MessageRepository messageRepository;

    public MessagePersistenceAdapter(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Given a Message, save it to the database
     *
     * @param message The Message to be saved
     */
    @Override
    public void saveMessage(Message message) {

    }

    /**
     * Given a Message that already exists, update the database Model to the given Message's read status
     *
     * @param message The message to be updated
     */
    @Override
    public void updateMessageReadStatus(Message message) {

    }

    /**
     * Given a Message that already exists, update the database Model to the given Message's archived status
     *
     * @param message The message to be updated
     */
    @Override
    public void updateMessageArchivedStatus(Message message) {

    }

    /**
     * Given a Message that already exists, update the database Model to the given Message's archived status
     *
     * @param message The message to be updated
     */
    @Override
    public void updateMessageDeletedStatus(Message message) {

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