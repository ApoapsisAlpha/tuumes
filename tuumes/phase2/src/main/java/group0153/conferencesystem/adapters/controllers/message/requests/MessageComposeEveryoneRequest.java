package group0153.conferencesystem.adapters.controllers.message.requests;

/**
 * A class facilitating the user's creation of messages by storing the information they input for everyone.
 */
public class MessageComposeEveryoneRequest {
    private String type;
    private String userId;
    private String content;

    /**
     * Gets content.
     *
     * @return Value of content.
     */
    public String getContent() {
        return content;
    }

    /**
     ll     * Gets userId.
     *
     * @return Value of userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets type.
     *
     * @return Value of type.
     */
    public String getType() {
        return type;
    }
}
