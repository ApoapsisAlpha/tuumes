package group0153.conferencesystem.application.event.exception;

public class EventNotFoundException extends Exception {
    private String field;

    public EventNotFoundException(String field) {super("Invalid event id " + field);}

    /**
     * Gets field.
     * @return Value of field.
     */
    public String getField() {
        return field;
    }
}
