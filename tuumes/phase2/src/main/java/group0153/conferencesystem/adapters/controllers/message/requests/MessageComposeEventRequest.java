package group0153.conferencesystem.adapters.controllers.message.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the user's creation of messages sent to attendees of an event by storing the information
 * they input.
 */
public class MessageComposeEventRequest {
    private final String content;
    private final String eventId;
    private final String userId;

    /**
     * Construct an instance of MessageComposeResource using the provided information about a message.
     *
     * @param content String content of the message
     * @param eventId String id of the event to message
     * @param userId  String id of the user that sent this message
     */
    public MessageComposeEventRequest(String content, String eventId, String userId) {
        this.content = content;
        this.eventId = eventId;
        this.userId = userId;
    }

    /**
     * Get recipient email.
     *
     * @return Value of eventId.
     */
    public String getEventId() throws InvalidInputException {
        if(eventId.isEmpty()){
            throw new InvalidInputException("event id");
        }
        return eventId;
    }

    /**
     * Get content of message.
     *
     * @return Value of content.
     */
    public String getContent() throws InvalidInputException {
        if(content.isEmpty()){
            throw new InvalidInputException("message content");
        }
        return content;
    }

    /**
     * Get id of user.
     *
     * @return Value of userId.
     */
    public String getUserId() throws InvalidInputException {
        if(userId.isEmpty()){
            throw new InvalidInputException("user id");
        }
        return userId;
    }
}
