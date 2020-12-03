package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.room.Room;

import java.util.List;

// uhhh, this doesn't work lol
public class Response {
//    private final List<?> data;
    private boolean valid;
    private String response;

    /**
     * Constructor for the response object.
     *
     * @param valid Whether the response is valid.
     * @param data The data included with the response.
     */
    public Response(boolean valid, Room data) {
        this.valid = valid;
//        this.data = (Room) data;
        this.response = valid ? "SUCCESS" : "FAILED";
    }

    /**
     * Sets new response.
     *
     * @param response New value of response.
     */
    public void setResponse(String response) {
        this.response = response;
    }
}
