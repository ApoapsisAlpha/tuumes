package group0153.conferencesystem.adapters.controllers.message.requests;

import java.util.List;

public class MessageComposeMultiEventRequest {
    private final String content;
    private final List<String> eventIds;
    private final String userId;

    /**
     * Construct an instance of MessageComposeResource using the provided information about a message
     *
     * @param content        String content of the message
     * @param eventIds       String ids of the events to message
     * @param userId         String id of the user that sent this message
     */
    public MessageComposeMultiEventRequest(String content, List<String> eventIds, String userId) {
        this.content = content;
        this.eventIds = eventIds;
        this.userId = userId;
    }

    /**
     * Get recipient email.
     *
     * @return Value of eventIds.
     */
    public List<String> getEventIds() {
        return eventIds;
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
