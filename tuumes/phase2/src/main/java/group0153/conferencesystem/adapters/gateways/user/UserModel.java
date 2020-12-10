package group0153.conferencesystem.adapters.gateways.user;

import group0153.conferencesystem.entities.user.UserType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;

@Entity
public class UserModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String name;
    private String email;
    private String password;
    private UserType type;
    private HashSet<String> events;
    private HashSet<String> contacts;
    private HashSet<String> messages;

    /**
     * Constructs an instance of UserModel using the provided information
     *
     * @param resourceId the id of the user
     * @param name       the name of the user
     * @param email      the email of the user
     * @param password   the password of the user
     * @param type       the type of the user (User, Organizer, Speaker, Vip)
     * @param events     the HashSet of ids of events the user is signed up for
     * @param contacts   the HashSet of ids of contacts of the user
     */
    public UserModel(String resourceId, String name, String email, String password, UserType type,
                     HashSet<String> events, HashSet<String> contacts) {
        this.resourceId = resourceId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.events = events;
        this.contacts = contacts;
    }

    /**
     * Constructs an instance of UserModel
     */
    public UserModel() {
    }

    /**
     * Get id of user
     *
     * @return string id of user
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Get name of user
     *
     * @return string value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Get email of user
     *
     * @return string value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get password of user
     *
     * @return string value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get type
     *
     * @return enum type of user
     */
    public UserType getType() {
        return type;
    }

    /**
     * Get ids of events user is signed up for
     *
     * @return HashSet of event ids
     */
    public HashSet<String> getEvents() {
        return events;
    }

    /**
     * Get ids of contacts of user
     *
     * @return HashSet of contact ids
     */
    public HashSet<String> getContacts() {
        return contacts;
    }

    /**
     * Get ids of messages sent to user
     *
     * @return HashSet of message ids
     */
    public HashSet<String> getMessages() {
        return messages;
    }

    // TODO: Speaker's speakingEventIds
}
