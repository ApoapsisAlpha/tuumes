package group0153.conferencesystem.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("User " + userId + " not found.");
    }
}
