package group0153.conferencesystem.adapters.controllers.user.requests;

/**
 * A class facilitating the user's interaction with their contacts by storing information about them
 */
public class UserAddContactRequest {
    private final String userId;
    private final String contactEmail;

    /**
     * Construct an instance of UserContactResource using the user's id and contact's id as provided
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
    public String getUserId() {
        return userId;
    }

    /**
     * Get the id of the contact.
     *
     * @return Value of contactId.
     */
    public String getContactEmail() {
        return contactEmail;
    }
}
