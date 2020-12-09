package group0153.conferencesystem.application.user.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.entities.user.UserType;

public class UserLoginData implements Data {
    private String id;
    private UserType userType;

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
