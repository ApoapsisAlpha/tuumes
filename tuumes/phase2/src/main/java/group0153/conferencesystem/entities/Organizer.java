package group0153.conferencesystem.entities;

public class Organizer extends User {

    /**
     * Constructor that instantiates an Organizer instance.
     *
     * @param name     name of organizer
     * @param email    email of organizer
     * @param password password of organizer account
     */
    public Organizer(String name, String email, String password) {
        super(name, email, password);
        type = UserType.ORGANIZER;
    }
}
