package group0153.conferencesystem.application.message.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class for storing information of a message.
 */
public class MessageData implements Data {
    private final String id; // id of the message
    private final String messageContent; // string containing the message content
    private final String senderId; // the sender's id
    private final List<String> recipientIds;

    private Set<String> readSet;
    private Set<String> archivedSet;
    private Set<String> deletedSet;

    /**
     * Construct a new instance of MessageData using the provided Message
     *
     * @param message Message instance whose attributes are to be preserved
     */
    public MessageData(Message message) {
        this.id = message.getId();
        this.messageContent = message.getMessageContent();
        this.senderId = message.getSenderId();
        this.recipientIds = message.getRecipientIds();
        this.readSet = message.getReadSet();
        this.archivedSet = message.getArchivedSet();
        this.deletedSet = message.getDeletedSet();
    }

    /**
     * Get the message id
     *
     * @return value of String message id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the message content
     *
     * @return value of String message content
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * Get the sender id
     *
     * @return value of String sender id
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Get the ArrayList of recipient ids
     *
     * @return ArrayList of String recipient ids
     */
    public List<String> getRecipientIds() {
        return recipientIds;
    }

    /**
     * Get whether the message represented by this MessageData instance has been read
     *
     * @param recipientId recipient id
     * @return boolean whether message has been read
     */
    public boolean isRead(String recipientId) {
        return readSet.contains(recipientId);
    }

    /**
     * Get whether the message represented by this MessageData instance is archived
     *
     * @param recipientId recipient id
     * @return boolean whether message is archived
     */
    public boolean isArchived(String recipientId) {
        return archivedSet.contains(recipientId);
    }

    /**
     * Get whether the message represented by this MessageData instance is deleted
     *
     * @param recipientId recipient id
     * @return boolean whether message is deleted
     */
    public boolean isDeleted(String recipientId) {
        return deletedSet.contains(recipientId);
    }
}
