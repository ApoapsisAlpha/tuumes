package group0153.conferencesystem.adapters.gateways.message;

import group0153.conferencesystem.adapters.gateways.user.UserModel;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MessageModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String content;
    private boolean isRead;

    @OneToOne
    private UserModel sender;

    @ManyToMany
    private Set<UserModel> recipients;

    public MessageModel() {

    }

    public MessageModel(String resourceId, String messageContent, UserModel sender, Set<UserModel> recipients, boolean isRead){
        this.resourceId = resourceId;
        this.content = messageContent;
        this.sender = sender;
        this.recipients = recipients;
        this.isRead = isRead;
    }

    public String getResourceId() { return resourceId; }

    public String getMessageContent() { return content; }

    public UserModel getSenderId() { return sender; }

    public Set<UserModel> getRecipientIds() { return recipients; }

    public boolean isRead() { return isRead; }
}
