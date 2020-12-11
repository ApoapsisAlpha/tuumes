package group0153.conferencesystem.adapters.controllers;

/**
 * A class facilitating the response of the program about whether the user's request has successfully completed
 */
public class Response {
    private final Boolean valid;
    private final String message;

    /**
     * Construct an instance of Response with the provided information
     *
     * @param valid a boolean representing whether the operation completed successfully
     * @param message a message to be displayed to the user about the operation
     */
    public Response(Boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    /**
     * Construct an instance of Response with the provided boolean stating the status
     *
     * @param valid boolean representing whether the operation was completed successfully
     */
    public Response(Boolean valid) {
        this.valid = valid;
        this.message = valid ? "SUCCESS" : "FAILED";
    }

    /**
     * Gets message.
     *
     * @return Value of message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets valid.
     *
     * @return Value of valid.
     */
    public Boolean getValid() {
        return valid;
    }
}
