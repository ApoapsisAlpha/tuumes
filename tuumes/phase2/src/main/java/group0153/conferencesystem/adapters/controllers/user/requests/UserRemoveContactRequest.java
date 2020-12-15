package group0153.conferencesystem.adapters.controllers.user.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the user's interaction with their contacts by storing information about them.
 */
public class UserRemoveContactRequest {
    private String userId;
    private String contactId;

    /**
     * Get the id of the user.
     *
     * @return Value of userId.
     * @throws InvalidInputException when the user id is not specified
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
     * @throws InvalidInputException when the contact id is not specified
     */
    public String getContactId() throws InvalidInputException {
        if (contactId.isEmpty())
            throw new InvalidInputException("contactId");

        return contactId;
    }
}