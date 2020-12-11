package group0153.conferencesystem.application.message;

import group0153.conferencesystem.application.message.exception.MessageIdNotFoundException;
import group0153.conferencesystem.application.message.exception.NoArchivedMessagesException;
import group0153.conferencesystem.application.message.exception.NoMessagesReceivedException;
import group0153.conferencesystem.application.message.exception.NoMessagesSentException;
import group0153.conferencesystem.entities.message.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class MessageGetterManager {
    final MessagePersistencePort messagePersistencePort;

    /**
     * Instantiates a MessageManager.
     *
     * @param messagePersistencePort How the messages are saved to the database.
     */
    public MessageGetterManager(MessagePersistencePort messagePersistencePort) {
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
        ArrayList<String> msgIds = messagePersistencePort.getMsgsBySender(sender);
        if(!msgIds.isEmpty())
            throw new NoMessagesSentException();
        return msgIds;
    }

    /**
     * Given a user's id, find the id(s)'s of all the messages sent to that user including archived ones.
     *
     * @param user recipient of the message(s)
     * @return A list of message ids
     * @throws NoMessagesReceivedException No messages have been received by recipient
     */
    public ArrayList<String> findMsgIdsByRecipient(String user){
        ArrayList<String> msgIds = messagePersistencePort.getMsgsToUser(user);
        if(msgIds.isEmpty())
            throw new NoMessagesReceivedException();
        return msgIds;
    }

    /**
     * Given user's id, get all their archived messages
     * @param user: id of the user whose archived messages are being returned
     * @return List of all archived message ids
     * @throws NoArchivedMessagesException No messages have been archived by recipient
     */
    public ArrayList<String> getArchivedMsgsByUser(String user){
        ArrayList<String> msgIds = messagePersistencePort.getMsgsToUser(user);
        if(msgIds.isEmpty())
            throw new NoArchivedMessagesException();

        return msgIds;
    }
}