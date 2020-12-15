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
     * Precondition: id is a valid id belonging to a user.
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

}
