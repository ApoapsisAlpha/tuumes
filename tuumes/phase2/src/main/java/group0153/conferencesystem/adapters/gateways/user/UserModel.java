package group0153.conferencesystem.adapters.gateways.user;

import group0153.conferencesystem.adapters.gateways.event.EventModel;
import group0153.conferencesystem.adapters.gateways.message.MessageModel;
import group0153.conferencesystem.entities.user.UserType;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserType type;

    @ManyToMany
    private Set<EventModel> events;

    @ManyToMany
    private Set<UserModel> contacts;

    @ManyToMany
    private Set<MessageModel> messages;

    public UserModel() {

    }

    /**
     * Constructs an instance of UserModel using the provided information
     *
     * @param resourceId the id of the user
     * @param name       the name of the user
     * @param email      the email of the user
     * @param password   the password of the user
     * @param type       the type of the user (User, Organizer, Speaker, Vip)
     */
    public UserModel(String resourceId, String name, String email, String password, UserType type) {
        this.resourceId = resourceId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
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
}
