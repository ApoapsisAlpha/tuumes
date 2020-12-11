package group0153.conferencesystem.entities.user;

import java.util.ArrayList;

/**
 * An entity class for speakers, subclass of User.
 * Speaker class for a user who is a speaker.
 */
public class Speaker extends User {
    private final ArrayList<String> speakingEventIds;

    /**
     * Constructor that initiates a Speaker instance.
     *
     * @param id       id of the speaker.
     * @param name     name of the speaker.
     * @param email    email of the speaker.
     * @param password password of the speaker account.
     */
    public Speaker(String id, String name, String email, String password) {
        super(id, name, email, password);
        type = UserType.SPEAKER;
        this.speakingEventIds = new ArrayList<>();
    }

    /**
     * Return the list of ids of Events that this speaker is speaking at.
     *
     * @return get list of event ids this speaker is speaking.
     */
    public ArrayList<String> getSpeakingEventIds() {
        return this.speakingEventIds;
    }

    /**
     * add an event id to the list of event ids this speaker is speaking.
     *
     * @param id the event id to add.
     */
    public void addSpeakingEventById(String id) {
        this.speakingEventIds.add(id);
    }

    /**
     * remove an event id from the list of event ids this speaker is speaking.
     *
     * @param id the event id to remove.
     */
    public void removeSpeakingEventById(String id) {
        this.speakingEventIds.remove(id);
    }
}
