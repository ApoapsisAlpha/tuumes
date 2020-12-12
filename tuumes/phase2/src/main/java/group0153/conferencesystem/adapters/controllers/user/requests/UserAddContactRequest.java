package group0153.conferencesystem.adapters.controllers.user.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the user's interaction with their contacts by storing information about them.
 */
public class UserAddContactRequest {
    private final String userId;
    private final String contactEmail;

    /**
     * Construct an instance of UserContactResource using the user's id and contact's id as provided.
     *
     * @param userId    the String id of the current user
     * @param contactEmail the String email of the user's contact
     */
    public UserAddContactRequest(String userId, String contactEmail) {
        this.userId = userId;
        this.contactEmail = contactEmail;
    }

    /**
     * Get the id of the user.
     *
     * @return Value of userId.
     */
    public String getUserId() throws InvalidInputException {
        if(userId.isEmpty())
            throw new InvalidInputException("userId");

        return userId;
    }

    /**
     * Get the id of the contact.
     *
     * @return Value of contactId.
     */
    public String getContactEmail() throws InvalidInputException{
        if(contactEmail.isEmpty())
            throw new InvalidInputException("contactEmail");

        return contactEmail;
    }
}
