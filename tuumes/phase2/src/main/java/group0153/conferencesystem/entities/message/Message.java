package group0153.conferencesystem.entities.message;

import java.util.ArrayList;

/**
 * Message class for an object that contains the message id, content,
 * sender id, list of recipients, and read status of the message.
 */

public abstract class Message {
    private final String id; // id of the message
    private final String messageContent; // string containing the message content
    private final String senderId; // the sender's id
    private boolean isRead;

    /**
     * Constructor that instantiates a Message instance.
     *
     * @param id: id of the message
     * @param messageContent: String with the message's content
     * @param senderId: id of sender
     */
    public Message(String id, String messageContent, String senderId) {
        this.id = id;
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.isRead = false;
    }

    /**
     * Getter for message id
     * @return message id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for message content
     * @return message string
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * Getter for sender id
     * @return sender id
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Getter for read status
     * @return true if message has been read
     */
    public boolean isRead() {
        return isRead;
    }

    /**
     * Setter for read status
     * @param read: if true, message is read by user
     */
    public void setRead(boolean read) {
        isRead = read;
    }

}