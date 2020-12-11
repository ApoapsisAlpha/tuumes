package group0153.conferencesystem.adapters.controllers.message.resource;

public class MessageResource {
    private String userId;
    private String messageId;

    public MessageResource(String userId, String messageId) {
        this.userId = userId;
        this.messageId = messageId;
    }

    /**
     * Gets messageId.
     *
     * @return Value of messageId.
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Gets userId.
     *
     * @return Value of userId.
     */
    public String getUserId() {
        return userId;
    }
}
