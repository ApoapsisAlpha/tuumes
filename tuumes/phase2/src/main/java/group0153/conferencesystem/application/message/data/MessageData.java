package group0153.conferencesystem.application.message.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.user.data.UserContactData;
import group0153.conferencesystem.entities.message.Message;
import group0153.conferencesystem.entities.user.User;

/**
 * A class for storing information of a message.
 */
public class MessageData implements Data {
    private final String id;
    private final String content;
    private final UserContactData sender;
    private final boolean isRead;
    private final boolean isArchived;
    private final boolean isDeleted;

    /**
     * Constructs a message data object for a specific recipient.
     *
     * @param message the message
     * @param sender the sender
     * @param recipientId the recipient's id
     */
    public MessageData(Message message, User sender, String recipientId) {
        this.id = message.getId();
        this.content = message.getMessageContent();
        this.sender = new UserContactData(sender.getId(), sender.getName(), sender.getEmail());
        this.isRead = message.isRead(recipientId);
        this.isArchived = message.isArchived(recipientId);
        this.isDeleted = message.isDeleted(recipientId);
    }

    /**
     * Get the message id.
     *
     * @return message id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the content
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the data for the sender.
     *
     * @return sender data
     */
    public UserContactData getSender() {
        return sender;
    }

    /**
     * Get read status.
     *
     * @return true/false depending on whether the message is read
     */
    public boolean isRead() {
        return isRead;
    }

    /**
     * Get archived status.
     *
     * @return true/false depending on whether the message is archived
     */
    public boolean isArchived() {
        return isArchived;
    }

    /**
     * Get deleted status.
     *
     * @return true/false depending on whether the message is deleted
     */
    public boolean isDeleted() {
        return isDeleted;
    }
}
