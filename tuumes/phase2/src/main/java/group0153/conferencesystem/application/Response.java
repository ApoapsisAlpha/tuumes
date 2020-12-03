package group0153.conferencesystem.application;

public class Response {
    private boolean valid;
    private Object data;

    /**
     * Constructor for the response object.
     * @param valid Whether the response is valid.
     * @param data The data included with the response.
     */
    public Response(boolean valid, Object data) {
        this.valid = valid;
        this.data = data;
    }
}
