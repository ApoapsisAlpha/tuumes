package group0153.conferencesystem.adapters.gateways;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class MessageModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String messageContent;
    private String senderId;
    private ArrayList<String> recipientIds;
    private boolean isRead;

    public MessageModel(String resourceId, String messageContent, String senderId, ArrayList<String> recipientIds, boolean isRead){
        this.resourceId = resourceId;
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.recipientIds = recipientIds;
        this.isRead = isRead;
    }

    public String getResourceId() { return resourceId; }

    public String getMessageContent() { return messageContent; }

    public String getSenderId() { return senderId; }

    public ArrayList<String> getRecipientIds() { return recipientIds; }

    public boolean isRead() { return isRead; }
}
