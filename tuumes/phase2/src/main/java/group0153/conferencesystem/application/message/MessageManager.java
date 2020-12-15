package group0153.conferencesystem.application.message;

import group0153.conferencesystem.application.exceptions.MessageIdNotFoundException;
import group0153.conferencesystem.entities.message.Message;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * A message use case class that has setters and getters for variables within a message.
 */
@Component
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
     * @param userId the id of the user who is changing read state
     * @param read boolean state of message
     * @throws MessageIdNotFoundException Thrown if message id doesn't exist
     */
    public void setMsgReadStatusById(String msgId, String userId, boolean read){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        if(read){
            message.get().addRead(userId);
        } else {
            message.get().removeRead(userId);
        }
        messagePersistencePort.updateMessageReadStatus(message.get().getId(), userId, read);
    }

    /**
     * Given message id, set its archived status to the given archived status
     * @param msgId The id of the message whose archived status is changing
     * @param userId The id of the user who is changing archive status
     * @param archived The new archived status
     * @throws MessageIdNotFoundException No message with that id exists
     */
    public void setArchivedStatusById(String msgId, String userId, boolean archived){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        if (archived){
            message.get().addArchived(userId);
        } else {
            message.get().removeArchived(userId);
        }
        messagePersistencePort.updateMessageArchivedStatus(message.get().getId(), userId, archived);
    }

    /**
     * Given message id, set its deleted status to the given deleted status
     * @param msgId The id of the message whose deleted status is changing
     * @param userId The id of the user who is deleting
     * @param deleted The new deleted status
     */
    public void setDeletedStatusById(String msgId, String userId, boolean deleted){
        Optional<Message> message = messagePersistencePort.findById(msgId);
        if(!message.isPresent())
            throw new MessageIdNotFoundException();

        if(deleted){
            message.get().addDeleted(userId);
        } else {
            message.get().removeDeleted(userId);
        }
        messagePersistencePort.updateMessageDeletedStatus(message.get().getId(), userId, deleted);
    }

}
