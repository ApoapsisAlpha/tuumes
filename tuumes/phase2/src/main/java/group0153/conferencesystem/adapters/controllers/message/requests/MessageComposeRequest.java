package group0153.conferencesystem.adapters.controllers.message.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the user's creation of messages by storing the information they input.
 */
public class MessageComposeRequest {
    private String content;
    private String recipientEmail;
    private String userId;

    /**
     * Get recipient email.
     *
     * @return Value of recipientEmail.
     * @throws InvalidInputException when the recipient email was not inputted by the user
     */
    public String getRecipientEmail() throws InvalidInputException {
        if (recipientEmail.isEmpty()) {
            throw new InvalidInputException("email");
        }
        return recipientEmail;
    }

    /**
     * Get content of message.
     *
     * @return Value of content.
     * @throws InvalidInputException when the message content was not inputted by the user
     */
    public String getContent() throws InvalidInputException {
        if (content.isEmpty()) {
            throw new InvalidInputException("message content");
        }
        return content;
    }

    /**
     * Get id of user.
     *
     * @return Value of userId.
     * @throws InvalidInputException when the user id does not correspond to a stored user
     */
    public String getUserId() throws InvalidInputException {
        if (userId.isEmpty()) {
            throw new InvalidInputException("user id");
        }
        return userId;
    }
}
