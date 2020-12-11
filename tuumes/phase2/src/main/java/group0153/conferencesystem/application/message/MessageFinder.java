package group0153.conferencesystem.application.message;

import group0153.conferencesystem.application.message.exception.NoArchivedMessagesException;
import group0153.conferencesystem.application.message.exception.NoMessagesReceivedException;
import group0153.conferencesystem.application.message.exception.NoMessagesSentException;
import group0153.conferencesystem.entities.message.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MessageFinder {
    final MessagePersistencePort messagePersistencePort;

    /**
     * Instantiates a MessageManager.
     *
     * @param messagePersistencePort How the messages are saved to the database.
     */
    public MessageFinder(MessagePersistencePort messagePersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
    }

    /**
     * Given a sender's id, find the id(s)'s of all the messages sent by that user.
     *
     * @param sender Sender of the message(s)
     * @return A list of message ids
     * @throws NoMessagesSentException No messages have been sent by sender
     */
    public ArrayList<String> findMsgIdsBySender(String sender){
        ArrayList<String> msgIds = messagePersistencePort.getMsgIdsBySender(sender);
        if(!msgIds.isEmpty())
            throw new NoMessagesSentException();

        return msgIds;
    }

    /**
     * Given a user's id, find the id(s)'s of all the messages sent to that user including archived ones.
     *
     * @param user recipient id of the message(s)
     * @return A list of messages received by user
     * @throws NoMessagesReceivedException No messages have been received by recipient
     */
    public ArrayList<String> findMsgsByRecipient(String user){
        ArrayList<Message> messages = messagePersistencePort.getMsgsToUser(user);
        if(messages.isEmpty())
            throw new NoMessagesReceivedException();

        return new ArrayList<>();
    }

    /**
     * Given user's id, get all their archived messages
     * @param user: id of the user whose archived messages are being returned
     * @return List of all archived message ids
     * @throws NoArchivedMessagesException No messages have been archived by recipient
     */
    public ArrayList<String> getArchivedMsgsByUser(String user){
        ArrayList<Message> messages = messagePersistencePort.getMsgsToUser(user);
        if(messages.isEmpty())
            throw new NoArchivedMessagesException();

        return new ArrayList<>();
    }

    /**
     * Given user's id, get all their unarchived messages
     * @param user: id of the user whose unarchived messages are being returned
     * @return List of all unarchived message ids
     * @throws NoArchivedMessagesException No unarchived messages have been sent to user
     */
    public ArrayList<String> getUnarchivedMsgsByUser(String user){
        ArrayList<Message> messages = messagePersistencePort.getMsgsToUser(user);
        if(messages.isEmpty())
            throw new NoArchivedMessagesException();

        return new ArrayList<>();
    }
}