package group0153.conferencesystem.exceptions.eventExceptions;

public class EventNotFoundException extends UnsuccessfulCommandException {
    public EventNotFoundException() {super("The event could not be found");}
}

