package group0153.conferencesystem.entities;

import java.util.ArrayList;
import java.util.Date;

public class MultiSpeakerEvent extends Event {
    private ArrayList<String> speakerIds;

    public ArrayList<String> getSpeakerIds;

    public MultiSpeakerEvent(String id, String eventName, String description, Date startTime, Date endTime,
                             String room, int userLimit, boolean isVipOnlyEvent, ArrayList<String> speakerIds) {
        super(id, eventName, description, startTime, endTime, room, userLimit, isVipOnlyEvent);
        this.speakerIds = speakerIds;
    }

    /**
     *
     * @param speakerId The id of the speaker to be added.
     * @return Returns "The speaker has been added succesfully to this event." because this is a multi-speaker event.
     */
    public String addSpeakerId(String speakerId) {
        this.speakerIds.add(speakerId);
        return "The speaker has been added successfully to this multi-speaker event.";
    }
    public void removeSpeakerId(String speakerId) { this.speakerIds.remove(speakerId); }
}
