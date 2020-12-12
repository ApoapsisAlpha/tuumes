package group0153.conferencesystem.adapters.controllers.message.requests;

/**
 * A class facilitating the user's creation of messages by storing the information they input.
 */
public class MessageComposeRequest {
    private final String content;
    private final String recipientEmail;
    private final String userId;

    /**
     * Construct an instance of MessageComposeResource using the provided information about a message.
     *
     * @param content        String content of the message
     * @param recipientEmail String email of the recipient of the message
     * @param userId         String id of the user that sent this message
     */
    public MessageComposeRequest(String content, String recipientEmail, String userId) {
        this.content = content;
        this.recipientEmail = recipientEmail;
        this.userId = userId;
    }

    /**
     * Get recipient email.
     *
     * @return Value of recipientEmail.
     */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    /**
     * Get content of message.
     *
     * @return Value of content.
     */
    public String getContent() {
        return content;
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
