package group0153.conferencesystem.adapters.controllers.message.resource;

/**
 * A class facilitating the user's interaction with messages by providing the information about them
 */
public class MessageResource {
    private final String userId;
    private final String messageId;

    /**
     * Construct an instance of MessageResource using the provided userId and messageId
     *
     * @param userId    the id of the user
     * @param messageId the id of the message
     */
    public MessageResource(String userId, String messageId) {
        this.userId = userId;
        this.messageId = messageId;
    }

    /**
     * Get id of message.
     *
     * @return Value of messageId.
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Get id of user.
     *
     * @return Value of userId.
     */
    public String getUserId() {
        return userId;
    }
}
