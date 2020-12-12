package group0153.conferencesystem.application.exceptions;

import group0153.conferencesystem.entities.user.UserType;

public class MissingPermissionException extends RuntimeException {
    public MissingPermissionException(UserType userType) {
        super("You need to be " + userType + " to use this");
    }
}
