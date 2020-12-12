package group0153.conferencesystem.application.user.data;

import group0153.conferencesystem.application.Data;

/**
 * A class containing the contact information of the current user of the program
 */

public class UserContactData implements Data {
    private String id;
    private String name;
    private String email;

    /**
     * Constructs a instance of UserContactData with the provided information.
     *
     * @param id    the String id of the contact
     * @param name  the String name of the contact
     * @param email the String email of the contact
     */
    public UserContactData(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets email.
     *
     * @return Value of email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public String getId() {
        return id;
    }
}