package group0153.conferencesystem.application.message;

import group0153.conferencesystem.application.message.exception.MessageIdNotFoundException;
import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;
import java.util.Optional;

/**
 * A message use case class that has setters and getters for variables within a message.
 */

public class MessageManager {
    final MessagePersistencePort messagePersistencePort;

    /**
     * Instantiates a MessageManager.
     *
     * @param messagePersistencePort How the messages are saved to the database.
     */
    public MessageManager(MessagePersistencePort messagePersistencePort){
        this.messagePersistencePort = messagePersistencePort;
    }

    /**
     * Given a message's id, set the message's read state.
     *
     * @param msgId the message id
     * @param read boolean state of message
     * @throws MessageIdNotFoundException Thrown if message id doesn't exist
     */
    public void setMsgReadStatusById(String msgId, boolean read){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        message.get().setRead(read);
        messagePersistencePort.updateMessageReadStatus(message.get());
    }

    /**
     * Given message id, set its archived status to the given archived status
     * @param msgId The id of the message whose archived status is changing
     * @param archived The new archived status
     * @throws MessageIdNotFoundException No message with that id exists
     */
    public void setArchivedStatusById(String msgId, boolean archived){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        message.get().setArchived(archived);
        messagePersistencePort.updateMessageArchivedStatus(message.get());
    }

    public void setDeletedStatusById(String msgId, boolean deleted){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        message.get().setArchived(deleted);
        messagePersistencePort.updateMessageDeletedStatus(message.get());
    }

    /**
     * Given a message's id, return the message's read state.
     *
     * @param msgId the message id
     * @return read boolean, true meaning the message has been read
     * @throws MessageIdNotFoundException Thrown if message id doesn't exist
     */
    public boolean getMsgReadStatusById(String msgId){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        return message.get().isRead();
    }

    /**
     * Given a message's id, return the message's content.
     *
     * @param msgId The message id of the message to return the content of
     * @return message content
     * @throws MessageIdNotFoundException Thrown if message doesn't exist
     */
    public String getMsgContentById(String msgId){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        return message.get().getMessageContent();
    }

    /**
     * Given a message's id, return the sender's id.
     *
     * @param msgId The message id of the message to return the sender of.
     * @return sender id
     * @throws MessageIdNotFoundException Thrown if message doesn't exist
     */
    public String getSenderIdByMsgId(String msgId){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        return message.get().getSenderId();
    }

    /**
     * Given a message's id, return the recipient(s)'s id(s).
     * @param msgId the message id of the message to return the recipient of.
     * @return A list of recipient id(s)
     */

    public ArrayList<String> getRecipientIdsByMsgId(String msgId){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        return message.get().getRecipientIds();
    }
}
