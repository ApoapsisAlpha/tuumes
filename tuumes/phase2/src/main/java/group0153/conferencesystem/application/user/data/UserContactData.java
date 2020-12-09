package group0153.conferencesystem.application.user.data;

import group0153.conferencesystem.application.Data;

public class UserContactData implements Data {
    private String id;
    private String name;
    private String email;

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
}