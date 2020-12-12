package group0153.conferencesystem.adapters.controllers.user.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the user's interaction with their contacts by storing information about them.
 */
public class UserRemoveContactRequest {
    private final String userId;
    private final String contactId;

    /**
     * Construct an instance of UserContactResource using the user's id and contact's id as provided.
     *
     * @param userId    the String id of the current user
     * @param contactId the String id of the user's contact
     */
    public UserRemoveContactRequest(String userId, String contactId) {
        this.userId = userId;
        this.contactId = contactId;
    }

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