package group0153.conferencesystem.entities.event;

import java.util.ArrayList;
import java.util.Date;

public class MultiSpeakerEvent extends Event {
    public ArrayList<String> getSpeakerIds;
    private final ArrayList<String> speakerIds;

    public MultiSpeakerEvent(String id, String eventName, String description, Date startTime, Date endTime,
                             String room, int userLimit, boolean isVipOnlyEvent, ArrayList<String> speakerIds) {
        super(id, eventName, description, startTime, endTime, room, userLimit, isVipOnlyEvent);
        this.speakerIds = speakerIds;
    }

    /**
     * @param speakerId The id of the speaker to be added.
     */
    public void addSpeakerId(String speakerId) {
        this.speakerIds.add(speakerId);
    }

    public void removeSpeakerId(String speakerId) {
        this.speakerIds.remove(speakerId);
    }
}
