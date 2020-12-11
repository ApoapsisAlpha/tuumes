package group0153.conferencesystem.application.exceptions;

public class FullRoomException extends RuntimeException {

    private String roomId;

    /**
     * Create an instance of the exception with the given room id.
     * @param roomId room id
     */
    public FullRoomException(String roomId) {
        super("Event " + roomId + " is full.");
        this.roomId = roomId;
    }

    /**
     * Get the room id.
     * @return room id
     */
    public String getRoomId() {
        return roomId;
    }
}
