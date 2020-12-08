package group0153.conferencesystem.application.user.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super("This user already exists!");
    }
}
