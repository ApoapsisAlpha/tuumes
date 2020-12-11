package group0153.conferencesystem.application.user.exception;

/**
 * Exception class that is thrown when there is no valid user found.
 */
public class UserExistsException extends RuntimeException {
    /**
     * Instantiates a UserExistsException.
     */
    public UserExistsException() {
        super("This user already exists!");
    }
}
