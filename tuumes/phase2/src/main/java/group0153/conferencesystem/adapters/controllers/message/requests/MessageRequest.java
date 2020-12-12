package group0153.conferencesystem.adapters.controllers.message.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the user's interaction with messages by providing the information about them.
 */
public class MessageRequest {
    private final String userId;
    private final String messageId;

    /**
     * Construct an instance of MessageResource using the provided userId and messageId.
     *
     * @param userId    the id of the user
     * @param messageId the id of the message
     */
    public MessageRequest(String userId, String messageId) {
        this.userId = userId;
        this.messageId = messageId;
    }

    /**
     * Get id of message.
     *
     * @return Value of messageId.
     * @throws InvalidInputException when the id of the message requested does not correspond to a stored message
     */
    public String getMessageId() throws InvalidInputException {
        if (messageId.isEmpty()) {
            throw new InvalidInputException("message id");
        }

        return messageId;
    }

    /**
     * Get id of user.
     *
     * @return Value of userId.
     * @throws InvalidInputException when the id of the user requested does not correspond to a stored user
     */
    public String getUserId() throws InvalidInputException {
        if (userId.isEmpty()) {
            throw new InvalidInputException("user id");
        }
        return userId;
    }
}
