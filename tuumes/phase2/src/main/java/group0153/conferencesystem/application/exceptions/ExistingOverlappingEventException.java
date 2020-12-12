package group0153.conferencesystem.application.exceptions;

public class ExistingOverlappingEventException extends RuntimeException {

    public ExistingOverlappingEventException() {
        super("There is an existing event at this time.");
    }

}
