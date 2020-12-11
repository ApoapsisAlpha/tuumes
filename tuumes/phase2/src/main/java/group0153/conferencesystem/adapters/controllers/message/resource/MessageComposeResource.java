package group0153.conferencesystem.adapters.controllers.message.resource;

/**
 * A class facilitating the user's interaction with another user so they can compose messages to them
 */
public class MessageComposeResource {
    private String content;
    private String recipientEmail;
    private String userId;

    /**
     * Construct an instance of MessageComposeResource using the user's id, recipient's email and the
     * content of the message
     * @param content         the String representing the message to be sent
     * @param recipientEmail  the String representing the email of the recipient
     * @param userId          the String id of the user sending the message
     */
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
