package group0153.conferencesystem.entities;

import java.util.ArrayList;

public class MultiSpeakerEvent extends Event {
    private ArrayList<String> speakerIds;

    public ArrayList<String> getSpeakerIds;

    /**
     *
     * @param speakerId The id of the speaker to be added.
     * @return Returns "The speaker has been added succesfully to this event." because this is a multi-speaker event.
     */
    public String addSpeakerId(String speakerId) {
        this.speakerIds.add(speakerId);
        return "The speaker has been added successfully to this event.";
    }
    public void removeSpeakerId(String speakerId) { this.speakerIds.remove(speakerId); }
}
