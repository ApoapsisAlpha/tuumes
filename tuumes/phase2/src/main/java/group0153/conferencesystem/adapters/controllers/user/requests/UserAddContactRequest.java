package group0153.conferencesystem.adapters.controllers.user.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the user's interaction with their contacts by storing information about them.
 */
public class UserAddContactRequest {
    private String userId;
    private String contactEmail;

    /**
     * Get the id of the user.
     *
     * @return Value of userId.
     * @throws InvalidInputException when there is no user id specified
     */
    public String getUserId() throws InvalidInputException {
        if (userId.isEmpty())
            throw new InvalidInputException("userId");

        return userId;
    }

    /**
     * Get the id of the contact.
     *
     * @return Value of contactId.
     * @throws InvalidInputException when there is no contact email specified
     */
    public String getContactEmail() throws InvalidInputException {
        if (contactEmail.isEmpty())
            throw new InvalidInputException("contactEmail");

        return contactEmail;
    }
}
