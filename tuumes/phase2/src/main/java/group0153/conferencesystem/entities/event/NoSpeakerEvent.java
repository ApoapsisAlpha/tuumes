package group0153.conferencesystem.entities.event;

import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;
import java.util.Date;

public class NoSpeakerEvent extends Event {
    public NoSpeakerEvent(String id, String eventName, String description, Date startTime, Date endTime,
                          String room, int userLimit, boolean isVipOnlyEvent) {
        super(id, eventName, description, startTime, endTime, room, userLimit, isVipOnlyEvent);
    }

    /**
     *
     * @return Returns "NoSpeakerEvent".
     */
    public String getEventType() { return "NoSpeakerEvent"; }

    @Override
    public void addSpeakerId(String speakerId) throws UnsuccessfulCommandException {
        throw new UnsuccessfulCommandException("This is a no-speaker event.");
    }

    @Override
    public void removeSpeakerId(String speakerId) throws UnsuccessfulCommandException {
        throw new UnsuccessfulCommandException("The");
    }

    @Override
    public ArrayList<String> getSpeakerIds() throws UnsuccessfulCommandException {
        return null;
    }

    public
}
