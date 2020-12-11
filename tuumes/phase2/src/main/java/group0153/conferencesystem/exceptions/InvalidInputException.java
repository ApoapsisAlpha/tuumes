package group0153.conferencesystem.exceptions;

public class InvalidInputException extends Exception {
    private String field;

    /**
     * Constructs a new instance of InvalidInputException with the provided data
     *
     * @param field a String specifying which input field has an invalid entry
     */
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
