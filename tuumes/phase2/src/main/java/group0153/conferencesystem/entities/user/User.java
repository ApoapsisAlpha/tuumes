package group0153.conferencesystem.entities.user;

import java.util.HashSet;
import java.util.Set;

/**
 * An entity class for all users, with sub-classes: Organizer, Speaker, Vip.
 * User class for an object that contains the id, list of contacts, list of events, user type, name,
 * email, and password of the user.
 */
public class User {

    /**
     * A builder for user.
     */
    public static class Builder {
        private String id;
        private String name;
        private String email;
        private String password;
        private UserType type;

        /**
         * set the user id.
         *
         * @param id id of user.
         * @return builder with desired id.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * set the user name.
         *
         * @param name name of user.
         * @return builder with desired name.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * set the user email.
         *
         * @param email email of user.
         * @return builder with desired email.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * set the user password.
         *
         * @param password name of user.
         * @return builder with desired password.
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * set the user type.
         *
         * @param type name of user.
         * @return builder with desired type.
         */
        public Builder type(UserType type) {
            this.type = type;
            return this;
        }

        /**
         * Return a new User instance with specified information
         *
         * @return user with desired type, name, email, password.
         */
        public User build() {
            User user;
            switch (type) {
                case SPEAKER:
                    user = new Speaker(id, name, email, password);
                    break;
                case ORGANIZER:
                    user = new Organizer(id, name, email, password);
                    break;
                case VIP:
                    user = new Vip(id, name, email, password);
                    break;
                default:
                    user = new User(id, name, email, password);
            }

            return user;
        }
    }

    private final String id;              // the id of this User
    private final Set<String> contacts;   // the set of ids of contacts of this User
    private final Set<String> events;     // the set of ids of events this User is registered for
    private final Set<String> messages;   // the set of ids of messages received by this User
    protected UserType type;              // the type of this User
    private String name;                  // the name of this User
    private String email;                 // the email of this User
    private String password;              // the password of this User

    /**
     * Constructor that instantiates a User instance.
     *
     * @param id       id of user.
     * @param name     name of user.
     * @param email    email of user.
     * @param password password of user account.
     */
    public User(String id, String name, String email, String password) {
        this.id = id;
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
     *
     * @return the name of the User
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email of the User
     *
     * @return the email of the User
     */
    public String getEmail() {
        return email;
    }

    /**
     * Changes the email stored in this User instance to email
     *
     * @param email the email that this User should change to
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the id of the User
     *
     * @return the id of the User
     */
    public String getId() {
        return id;
    }

    /**
     * Adds the id of the User to the HashMap representing this User's contact list.
     *
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
     * Get password of this user
     *
     * @return string value of password
     */
    public String getPassword() {
        return this.password;
    }


    /**
     * Removes the id of the User from the HashMap representing this User's contact list.
     *
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
     *
     * @param password Password to check
     * @return True iff passwords match
     */
    public boolean isPasswordValid(String password) {
        return this.password.equals(password);
    }

    /**
     * Return the Hashset of the ids of this User's contacts
     *
     * @return a HashSet containing the ids of this User's contacts.
     */
    public Set<String> getContacts() {
        return this.contacts;
    }

    /**
     * Return a Hashset of ids of events this User is signed up for
     *
     * @return the Hashset of ids of events the User is signed up for
     */
    public Set<String> getEvents() {
        return this.events;
    }

    /**
     * Remove the eventId from the list of events signed up for
     *
     * @param eventId the id of the event this User wishes to unregister from
     * @return a boolean whether the event was signed up for prior
     */
    public boolean removeEvent(String eventId) {
        return this.events.remove(eventId);
    }

    /**
     * Add the eventId to the list of events signed up for
     *
     * @param eventId the id of the event this User wishes to register for
     * @return a boolean whether the event was not signed up for prior
     */
    public boolean addEvent(String eventId) {
        return this.events.add(eventId);
    }

    /**
     * Add a message id to the set of ids corresponding to messages sent to this User
     *
     * @param messageId the id of the new message received by this User
     */
    public void addMessage(String messageId) {
        messages.add(messageId);
    }

    /**
     * Returns the user type.
     *
     * @return UserType enum
     */
    public UserType getType() {
        return type;
    }
}
