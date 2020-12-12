package group0153.conferencesystem.adapters.controllers.message.requests;

public class MessageComposeEventRequest {
    private final String content;
    private final String eventId;
    private final String userId;

    /**
     * Construct an instance of MessageComposeResource using the provided information about a message
     *
     * @param content        String content of the message
     * @param eventId        String id of the event to message
     * @param userId         String id of the user that sent this message
     */
    public MessageComposeEventRequest(String content, String eventId, String userId) {
        this.content = content;
        this.eventId = eventId;
        this.userId = userId;
    }

    /**
     * Get recipient email.
     *
     * @return Value of recipientEmail.
     */
    public String getEventId() {
        return eventId;
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
