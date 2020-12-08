package group0153.conferencesystem.adapters.controllers;

public class Response {
    private Boolean valid;
    private String message;

    public Response(Boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

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
