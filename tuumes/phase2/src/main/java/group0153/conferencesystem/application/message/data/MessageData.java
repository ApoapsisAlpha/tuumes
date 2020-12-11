package group0153.conferencesystem.application.message.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;

/**
 * A class for storing information of a message.
 */
public class MessageData implements Data {
    private final String id; // id of the message
    private final String messageContent; // string containing the message content
    private final String senderId; // the sender's id
    private final ArrayList<String> recipientIds;
    private boolean isRead;
    private boolean archived;

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
        this.isRead = message.isRead();
        this.archived = message.isArchived();
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
    public ArrayList<String> getRecipientIds() {
        return recipientIds;
    }

    /**
     * Get whether the message represented by this MessageData instance has been read
     *
     * @return boolean whether message has been read
     */
    public boolean isRead() {
        return isRead;
    }

    /**
     * Get whether the message represented by this MessageData instance is archived
     *
     * @return boolean whether message is archived
     */
    public boolean isArchived() {
        return archived;
    }
}
