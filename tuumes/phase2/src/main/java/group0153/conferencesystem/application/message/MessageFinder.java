package group0153.conferencesystem.application.message;

import group0153.conferencesystem.application.message.exception.*;
import group0153.conferencesystem.entities.message.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * A message use case class to find different messages depending on archived state,
 * sender, recipient, or read state.
 */

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
    private ArrayList<Message> findMsgsByRecipient(String user){
        ArrayList<Message> messages = messagePersistencePort.getMsgsToUser(user);
        if(messages.isEmpty())
            throw new NoMessagesReceivedException();

        return messages;
    }

    /**
     * Given user's id, get all their archived messages
     * @param user: id of the user whose archived messages are being returned
     * @return List of all archived message ids
     * @throws NoArchivedMessagesException No messages have been archived by recipient
     */
    public ArrayList<String> getArchivedMsgsByUser(String user){
        ArrayList<Message> messages = this.findMsgsByRecipient(user);
        ArrayList<String> archivedMsgIds = new ArrayList<>();

        if(messages.isEmpty())
            throw new NoArchivedMessagesException();
        else{
            for (Message message: messages){
                if(message.isArchived() && !message.isDeleted()){
                    archivedMsgIds.add(message.getId());
                }
            }
        }
        return archivedMsgIds;
    }

    /**
     * Given user's id, get all their unarchived messages
     * @param user: id of the user whose unarchived messages are being returned
     * @return List of all unarchived message ids
     * @throws NoUnarchivedMessagesException No unarchived messages have been sent to user
     */
    public ArrayList<String> getUnarchivedMsgsByUser(String user){
        ArrayList<Message> messages = this.findMsgsByRecipient(user);
        ArrayList<String> unarchivedMsgIds = new ArrayList<>();

        if(messages.isEmpty())
            throw new NoUnarchivedMessagesException();
        else{
            for (Message message: messages){
                if(!message.isArchived() && !message.isDeleted()){
                    unarchivedMsgIds.add(message.getId());
                }
            }
        }

        return unarchivedMsgIds;
    }

    /**
     * Given user's id, get all their read messages
     * @param user: id of the user whose read messages are being returned
     * @return List of all read message ids
     * @throws NoReadMessagesException A user has no unread messages.
     */
    public ArrayList<String> getReadMsgsByUser(String user){
        ArrayList<Message> messages = this.findMsgsByRecipient(user);
        ArrayList<String> readMsgIds = new ArrayList<>();

        if(messages.isEmpty())
            throw new NoReadMessagesException();
        else{
            for (Message message: messages){
                if(!message.isArchived() && message.isRead() && !message.isDeleted()){
                    readMsgIds.add(message.getId());
                }
            }
        }

        return readMsgIds;
    }

    /**
     * Given user's id, get all their unread messages
     * @param user: id of the user whose unread messages are being returned
     * @return List of all unread message ids
     * @throws NoUnreadMessagesException A user has no unread messages.
     */
    public ArrayList<String> getUnreadMsgsByUser(String user){
        ArrayList<Message> messages = this.findMsgsByRecipient(user);
        ArrayList<String> unreadMsgIds = new ArrayList<>();

        if(messages.isEmpty())
            throw new NoUnreadMessagesException();
        else{
            for (Message message: messages){
                if(!message.isArchived() && !message.isRead() && !message.isDeleted()){
                    unreadMsgIds.add(message.getId());
                }
            }
        }

        return unreadMsgIds;
    }
}