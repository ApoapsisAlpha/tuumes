package group0153.conferencesystem.entities;

import java.util.ArrayList;

public class MultiSpeakerEvent extends Event {
    private ArrayList<String> speakerIds;

    public ArrayList<String> getSpeakerIds;
    public String addSpeakerId(String speakerId) {
        this.speakerIds.add(speakerId);
        return "The speaker with has been added to this event.";
    }
    public void removeSpeakerId(String speakerId) { this.speakerIds.remove(speakerId); }
}
