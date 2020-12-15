package group0153.conferencesystem.application.exceptions;

/**
 *  An exception that is thrown when a speaker conflict occurs.
 */
public class SpeakerConflictException extends RuntimeException {

    /**
     * Instantiates a SpeakerConflictException where it's thrown when a speaker conflict occurs.
     */
    public SpeakerConflictException() {
        super("The speaker already has an event at this time.");
    }

}
