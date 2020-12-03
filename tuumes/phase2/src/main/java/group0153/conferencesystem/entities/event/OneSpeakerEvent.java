package group0153.conferencesystem.entities.event;

import group0153.conferencesystem.entities.event.Event;

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

    public String addSpeakerId(String speakerId) {
        if (this.speakerId.equals("")) return "This event already has a speaker. " +
                "Please remove the current speaker from this one-speaker event before adding a new speaker.";
        this.speakerId = speakerId;
        return "The speaker has been added successfully to this one-speaker event.";
    }
}
