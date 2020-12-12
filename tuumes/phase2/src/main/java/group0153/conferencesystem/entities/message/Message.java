package group0153.conferencesystem.entities.message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Message class for an object that contains the message id, content,
 * sender id, list of recipients, and read status of the message.
 */

public class Message {
    private final String id; // id of the message
    private final String messageContent; // string containing the message content
    private final String senderId; // the sender's id
    private final ArrayList<String> recipientIds;

    private final Set<String> readSet;
    private final Set<String> archivedSet;
    private final Set<String> deletedSet;

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
        this.readSet = new HashSet<>();
        this.archivedSet = new HashSet<>();
        this.deletedSet = new HashSet<>();
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
     * @param recipientId recipient id
     * @return true if message has been read
     */
    public boolean isRead(String recipientId) {
        return readSet.contains(recipientId);
    }

    /**
     * Getter for archived status
     * @param recipientId recipient id
     * @return true if message has been archived
     */
    public boolean isArchived(String recipientId) {
        return archivedSet.contains(recipientId);
    }

    /**
     * Getter for deleted status
     * @param recipientId recipient id
     * @return true if message has been deleted
     */
    public boolean isDeleted(String recipientId) {
        return deletedSet.contains(recipientId);
    }

    /**
     * Adds to readSet and user has read message
     * @param recipientId recipient id
     */
    public void addRead(String recipientId) {
        readSet.add(recipientId);
    }

    /**
     * Removes from readSet and user has unread message
     * @param recipientId recipient id
     */
    public void removeRead(String recipientId) {
        readSet.remove(recipientId);
    }

    /**
     * Add the message to archived status for user
     * @param recipientId recipient id
     */
    public void addArchived(String recipientId){
        archivedSet.add(recipientId);
    }

    /**
     * Removes the message from archived status for user
     * @param recipientId recipient id
     */
    public void removeArchived(String recipientId){
        archivedSet.remove(recipientId);
    }

    /**
     * Adds deleted status of message for user
     * @param recipientId recipient id
     */
    public void addDeleted(String recipientId) {
        deletedSet.add(recipientId);
    }

    /**
     * Removes deleted status of message for user
     * @param recipientId recipient id
     */
    public void removeDeleted(String recipientId) {
        deletedSet.remove(recipientId);
    }

    /**
     * Getter for recipient ids
     * @return Arraylist of recipient(s) id(s)
     */
    public ArrayList<String> getRecipientIds() {
        return recipientIds;
    }

}
