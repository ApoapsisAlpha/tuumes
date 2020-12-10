package group0153.conferencesystem.entities.user;

public class Organizer extends User {

    /**
     * Constructor that instantiates an Organizer instance.
     *
     * @param id       id of organizer
     * @param name     name of organizer
     * @param email    email of organizer
     * @param password password of organizer account
     */
    public Organizer(String id, String name, String email, String password) {
        super(id, name, email, password);
        type = UserType.ORGANIZER;
    }
}
