package group0153.conferencesystem.application.exceptions;

import group0153.conferencesystem.entities.user.UserType;

/**
 * An exception thrown when a user attempts to use functionality of the program that they do not have access to.
 */
public class MissingPermissionException extends RuntimeException {
    /**
     * Construct an instance of MissingPermissionException using the provided UserType instance.
     *
     * @param userType the UserType type of user that one must be in order to use a certain function of the program
     */
    public MissingPermissionException(UserType userType) {
        super("You need to be " + userType + " to use this");
    }
}
