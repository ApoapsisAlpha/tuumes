package group0153.conferencesystem.application.user.exception;

/**
 * Exception class that is thrown when there is an incorrect login.
 */
public class IncorrectLoginException extends RuntimeException {
    private String field;

    /**
     * Instantiates a IncorrectLoginException.
     * @param field The password that is incorrect.
     */
    public IncorrectLoginException(String field) {
        super("Incorrect login field " + field);
    }

    /**
     * Gets field.
     *
     * @return Value of field.
     */
    public String getField() {
        return field;
    }
}
