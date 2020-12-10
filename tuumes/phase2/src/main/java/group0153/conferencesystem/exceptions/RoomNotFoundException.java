package group0153.conferencesystem.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String roomId) {
        super("Room " + roomId + " not found.");
    }
}
