package group0153.conferencesystem.exceptions;

public class RoomNotFoundException extends RuntimeException {

    /**
     * Constructs an instance of RoomNotFoundException with the provided roomId
     * @param roomId a String specifying which room cannot be found
     */
    public RoomNotFoundException(String roomId) {
        super("Room " + roomId + " not found.");
    }
}
