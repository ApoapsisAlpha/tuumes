package group0153.conferencesystem.application.message;

import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;
import java.util.Optional;

public interface MessagePersistencePort {

    /**
     * Given a Message, save it to the database
     * @param message The Message to be saved
     */
    void saveMessage(Message message);

    /**
     * Given a Message that already exists, update the database Model to the given Message's read status
     * @param message The message to be updated
     */
    void updateMessageReadStatus(Message message);

    /**
     * Given a Message that already exists, update the database Model to the given Message's archived status
     * @param message The message to be updated
     */
    void updateMessageArchivedStatus(Message message);

    /**
     * Find an optional Message by id
     * @param msgId The id of the message being found
     * @return Returns an Optional Message with the given id
     */
    Optional<Message> findById(String msgId);

    /**
     * Given a user, returns a list of all Messages (archived and unarchived) that were sent to a user
     * @param user The id of the user whose Messages are being given
     * @return A List of all Messages of the given user
     */
    ArrayList<Message> getMsgsToUser(String user);

    /**
     * Given a senderid, returns a list of all Message ids sent by that sender
     * @param sender The id of the sender whose messages are being found
     * @return A List of all messages sent by the given sender
     */
    ArrayList<String> getMsgIdsBySender(String sender);

}
