package group0153.conferencesystem.adapters.controllers.user.resources;

public class UserContactResource {
    private String userId;
    private String contactId;

    public UserContactResource(String userId, String contactId) {
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * Gets userId.
     *
     * @return Value of userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets contactId.
     *
     * @return Value of contactId.
     */
    public String getContactId() {
        return contactId;
    }
}
