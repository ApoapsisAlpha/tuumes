package group0153.conferencesystem.entities;

import java.util.HashSet;
import java.util.UUID;

public class User {

    public static class Builder {
        private String name;
        private String email;
        private String password;
        private UserType type;

        /**
         * set the user name.
         * @param name name of user.
         * @return builder with desired name.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * set the user email.
         * @param email email of user.
         * @return builder with desired email.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * set the user password.
         * @param password name of user.
         * @return builder with desired password.
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * set the user type.
         * @param type name of user.
         * @return builder with desired type.
         */
        public Builder type(UserType type) {
            this.type = type;
            return this;
        }

        /**
         * @return user with desired type, name, email, password.
         */
        public User build() {
            User user;
            switch (type) {
                case SPEAKER:
                    user = new Speaker(name, email, password);
                    break;
                case ORGANIZER:
                    user = new Organizer(name, email, password);
                    break;
                default:
                    user = new User(name, email, password);
            }

            return user;
        }
    }

    private final String id;
    private String name;
    private String email;
    private String password;
    protected UserType type;
    private final HashSet<String> contacts;
    private final HashSet<String> events;
    private final HashSet<String> messages;

    /**
     * Constructor that instantiates a User instance.
     * @param name name of user.
     * @param email email of user.
     * @param password password of user account.
     */
    public User(String name, String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.type = UserType.ATTENDEE;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contacts = new HashSet<>();
        this.events = new HashSet<>();
        this.messages = new HashSet<>();
    }

    /**
     * Returns the name of the User
     * @return the name of the User
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email of the User
     * @return the email of the User
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the id of the User
     * @return the id of the User
     */
    public String getId() {
        return id;
    }

    /**
     * Changes the name stored in this User instance to name
     * @param name the name that this User should change to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the email stored in this User instance to email
     * @param email the email that this User should change to
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Adds the id of the User to the HashMap representing this User's contact list.
     * @param userId the id of the User to add to the list
     * @return a boolean whether the userId has been successfully added
     */
    public boolean addContact(String userId) {
        if (!contacts.contains(userId)) {
            contacts.add(userId);
            return true;
        }

        return false;
    }

    /**
     * Removes the id of the User from the HashMap representing this User's contact list.
     * @param userId the id of the User to remove from the list
     * @return a boolean whether the userId has been successfully removed
     */
    public boolean removeContact(String userId) {
        if (contacts.contains(userId)) {
            contacts.remove(userId);
            return true;
        }

        return false;
    }

    /**
     * Returns whether or not the password matches the user's set password.
     * @param password Password to check
     * @return True iff passwords match
     */
    public boolean isPasswordValid(String password) {
        return this.password.equals(password);
    }

    /**
     * Return the Hashet of the ids of this User's contacts
     *
     * @return a HashSet containing the ids of this User's contacts.
     */
    public HashSet<String> getContacts() {
        return this.contacts;
    }

    /**
     * Return a Hashset of ids of events this User is signed up for
     * @return the Hashset of ids of events the User is signed up for
     */
    public HashSet<String> getEvents() {
        return this.events;
    }

    /**
     * Remove the eventId from the list of events signed up for
     * @param eventId the id of the event this User wishes to unregister from
     * @return a boolean whether the event was signed up for prior
     */
    public boolean removeEvent(String eventId) {
        return this.events.remove(eventId);
    }

    /**
     * Add the eventId to the list of events signed up for
     * @param eventId the id of the event this User wishes to register for
     * @return a boolean whether the event was not signed up for prior
     */
    public boolean addEvent(String eventId) {
        return this.events.add(eventId);
    }

    /**
     * Returns the user type.
     * @return UserType enum
     */
    public UserType getType() {
        return type;
    }
}
