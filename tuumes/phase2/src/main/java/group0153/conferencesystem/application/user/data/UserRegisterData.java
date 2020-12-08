package group0153.conferencesystem.application.user.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.entities.user.UserType;

public class UserRegisterData implements Data {
    private String id;
    private UserType userType;

    public UserRegisterData(String id, UserType userType) {
        this.id = id;
        this.userType = userType;
    }

    /**
     * Gets userType.
     *
     * @return Value of userType.
     */
    public UserType getUserType() {
        return userType;
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


