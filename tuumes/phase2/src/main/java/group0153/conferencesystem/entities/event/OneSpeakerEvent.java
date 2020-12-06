package group0153.conferencesystem.entities.event;

import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;
import java.util.Date;

public class OneSpeakerEvent extends Event {
    private String speakerId;

    public OneSpeakerEvent(String id, String eventName, String description, Date startTime, Date endTime,
                           String room, int userLimit, boolean isVipOnlyEvent, String speakerId) {
        super(id, eventName, description, startTime, endTime, room, userLimit, isVipOnlyEvent);
        this.speakerId = speakerId;
    }

    public ArrayList<String> getSpeakerIds() {
        ArrayList<String> res = new ArrayList<String>();
        res.add(this.speakerId);
        return res;
    }

    public void addSpeakerId(String speakerId) throws UnsuccessfulCommandException {
        if (this.getSpeakerIds().size() != 0) throw new UnsuccessfulCommandException("This one-speaker event already has a speaker.");
        this.speakerId = speakerId;
    }

    /**
     *
     * @return Returns "OneSpeakerEvent".
     */
    public String getEventType() { return "OneSpeakerEvent"; }
}
