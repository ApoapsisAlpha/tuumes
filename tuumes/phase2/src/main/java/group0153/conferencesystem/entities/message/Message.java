package group0153.conferencesystem.entities.message;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Message class for an object that contains the message id, content,
 * sender id, list of recipients, and read status of the message.
 */

public class Message {
    private final String id; // id of the message
    private final String messageContent; // string containing the message content
    private final String senderId; // the sender's id
    private boolean isRead;
    private final ArrayList<String> recipientIds;

    /**
     * Constructor that instantiates a Message instance.
     * @param messageContent : String with the message's content
     * @param senderId       : id of sender
     * @param recipientIds   : an ArrayList of the recipient(s)'s id(s)
     */
    public Message(String messageContent, String senderId, ArrayList<String> recipientIds) {
        this.id = UUID.randomUUID().toString();
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.recipientIds = recipientIds;
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

    /**
     * Getter for recipient ids
     * @return Arraylist of recipient(s) id(s)
     */
    public ArrayList<String> getRecipientIds() {
        return recipientIds;
    }

}
