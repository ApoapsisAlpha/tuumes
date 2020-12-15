package group0153.conferencesystem.application.exceptions;

/**
 *  An exception that is thrown when a speaker conflict occurs.
 */
public class SpeakerConflictException extends RuntimeException {

    public SpeakerConflictException() {
        super("The speaker already has an event at this time.");
    }

}
