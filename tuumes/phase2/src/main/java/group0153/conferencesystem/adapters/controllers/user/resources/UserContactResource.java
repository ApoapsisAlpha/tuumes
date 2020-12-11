package group0153.conferencesystem.adapters.controllers.user.resources;

/**
 * A class facilitating the user's interaction with their contacts by storing information about them
 */
public class UserContactResource {
    private final String userId;
    private final String contactId;

    /**
     * Construct an instance of UserContactResource using the user's id and contact's id as provided
     *
     * @param userId    the String id of the current user
     * @param contactId the String id of the user's contact
     */
    public UserContactResource(String userId, String contactId) {
        this.userId = userId;
        this.contactId = contactId;
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
    public String getContactId() {
        return contactId;
    }
}
