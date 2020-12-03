package group0153.conferencesystem.application.user.exception;

public class InvalidInputException extends Exception {
    private String field;

    public InvalidInputException(String field) {
        super("Invalid input " + field);
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
