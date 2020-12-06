package group0153.conferencesystem.entities.event;

import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;
import java.util.Date;

public class MultiSpeakerEvent extends Event {
    private final ArrayList<String> speakerIds;
    private final String eventType = "MultiSpeakerEvent";

    public MultiSpeakerEvent(String id, String eventName, String description, Date startTime, Date endTime,
                             String room, int userLimit, boolean isVipOnlyEvent, ArrayList<String> speakerIds) {
        super(id, eventName, description, startTime, endTime, room, userLimit, isVipOnlyEvent);

        this.speakerIds = speakerIds;
    }

    /**
     * @param speakerId The id of the speaker to be added.
     */
    public void addSpeakerId(String speakerId) throws UnsuccessfulCommandException {
        for (String otherSpeakerId : this.getSpeakerIds()) {
            if (otherSpeakerId.equals(speakerId)) throw new UnsuccessfulCommandException("This speaker is already registered for this event.");
        }
    }

    public ArrayList<String> getSpeakerIds() { return this.speakerIds; }

    /**
     *
     * @param speakerId The id of the speaker to be removed.
     *                  If the speaker is not registered for this event, nothing happens.
     */
    public void removeSpeakerId(String speakerId) {
        this.speakerIds.remove(speakerId);
    }

    /**
     *
     * @return MultiSpeakerEvent
     */
    public String getEventType() { return "MultiSpeakerEvent"; }
}
