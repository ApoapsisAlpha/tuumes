package group0153.conferencesystem.adapters.controllers.message.resource;

public class MessageComposeResource {
    private String content;
    private String recipientEmail;
    private String userId;

    public MessageComposeResource(String content, String recipientEmail, String userId) {
        this.content = content;
        this.recipientEmail = recipientEmail;
        this.userId = userId;
    }

    /**
     * Gets recipientEmail.
     *
     * @return Value of recipientEmail.
     */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    /**
     * Gets content.
     *
     * @return Value of content.
     */
    public String getContent() {
        return content;
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
