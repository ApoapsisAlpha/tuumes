package group0153.conferencesystem.entities.message;

import java.util.ArrayList;

/**
 * Message class for an object that contains the message id, content,
 * sender id, list of recipients, and read status of the message.
 */

public class Message {
    private final String id; // id of the message
    private final String messageContent; // string containing the message content
    private final String senderId; // the sender's id
    private final ArrayList<String> recipientIds;
    private boolean isRead;
    private boolean archived;
    private boolean deleted;

    /**
     * Constructor that instantiates a Message instance.
     *
     * Precondition: id is a valid id belonging to a message.
     *               senderId is a valid id belonging to a user.
     *               every element in recipientIds is a valid id belonging to a user.
     *
     * @param id             : String id of this Message
     * @param messageContent : String with the message's content
     * @param senderId       : id of sender
     * @param recipientIds   : an ArrayList of the recipient(s)'s id(s)
     */
    public Message(String id, String messageContent, String senderId, ArrayList<String> recipientIds) {
        this.id = id;
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.recipientIds = recipientIds;
        isRead = false;
        archived = false;
        deleted = false;
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
     * Getter for archived status
     * @return true if message has been archived
     */
    public boolean isArchived() {
        return archived;
    }

    /**
     * Getter for deleted status
     * @return true if message has been deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Setter for read status
     * @param read: if true, message is read by user
     */
    public void setRead(boolean read) {
        isRead = read;
    }

    /**
     * Setter for archived status
     * @param archived: if true, message is archived by user
     */
    public void setArchived(boolean archived){
        this.archived = archived;
    }

    /**
     * Setter for deleted status
     * @param deleted: if true, message is deleted by user
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Getter for recipient ids
     * @return Arraylist of recipient(s) id(s)
     */
    public ArrayList<String> getRecipientIds() {
        return recipientIds;
    }

}
