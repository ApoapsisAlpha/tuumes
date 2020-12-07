package group0153.conferencesystem.application.user.exception;

public class IncorrectLoginException extends RuntimeException {
    private String field;

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
