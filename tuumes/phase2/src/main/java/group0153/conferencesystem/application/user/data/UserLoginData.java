package group0153.conferencesystem.application.user.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.entities.user.UserType;

/**
 * A class containing the id and type of the current user of the program
 */
public class UserLoginData implements Data {
    private final String id;
    private final UserType userType;

    /**
     * @param id       the id of the user logging in
     * @param userType the type of the user logging in
     */
    public UserLoginData(String id, UserType userType) {
        this.id = id;
        this.userType = userType;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets userType.
     *
     * @return Value of userType.
     */
    public UserType getUserType() {
        return userType;
    }
}
