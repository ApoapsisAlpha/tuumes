package group0153.conferencesystem.adapters.gateways.message;

import group0153.conferencesystem.adapters.gateways.user.UserModel;

import javax.persistence.*;
import java.util.Set;
/**
 * Class for database message entities.
 */
@Entity
public class MessageModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String content;

    @ManyToMany
    private Set<UserModel> readSet;

    @ManyToMany
    private Set<UserModel> archivedSet;

    @ManyToMany
    private Set<UserModel> deleteSet;

    @OneToOne
    private UserModel sender;

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<UserModel> recipients;

    /**
     * Instantiates a MessageModel with no specified information.
     */
    public MessageModel() {

    }

    /**
     * Instantiates a MessageModel.
     *
     * @param resourceId message id
     * @param messageContent string of message content
     * @param sender sender id
     * @param recipients list of recipient id(s)
     */
    public MessageModel(String resourceId, String messageContent, UserModel sender, Set<UserModel> recipients) {
        this.resourceId = resourceId;
        this.content = messageContent;
        this.sender = sender;
        this.recipients = recipients;
    }

    /**
     * Getter for resource id
     * @return id of message model
     */
    public String getResourceId() { return resourceId; }

    /**
     * Getter for message content
     * @return string of message content
     */
    public String getMessageContent() { return content; }

    /**
     * Getter for sender id
     * @return sender id
     */
    public UserModel getSender() { return sender; }

    /**
     * Getter for recipient id
     * @return array list of recipient id(s)
     */
    public Set<UserModel> getRecipients() { return recipients; }

    /**
     * Getter for the set of users that read the message.
     * @return set of user models
     */
    public Set<UserModel> getReadSet() {
        return readSet;
    }

    /**
     * Getter for the set of users that archived the message.
     * @return set of user models
     */
    public Set<UserModel> getArchivedSet() {
        return archivedSet;
    }

    /**
     * Getter for the set of users that deleted the message.
     * @return set of user models
     */
    public Set<UserModel> getDeleteSet() {
        return deleteSet;
    }
}
