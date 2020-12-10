package group0153.conferencesystem.exceptions;

public class UserNotFoundException extends RuntimeException {
    private String userId;

    public UserNotFoundException(String userId) {
        super("User " + userId + " not found.");
        this.userId = userId;
    }

    /**
     * Gets userId.
     *
     * @return Value of userId.
     */
    public String getUserId() {
        return userId;
    }
}
