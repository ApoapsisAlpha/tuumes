package group0153.conferencesystem.application.message.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;

public class MessageData implements Data {
    private final String id; // id of the message
    private final String messageContent; // string containing the message content
    private final String senderId; // the sender's id
    private final ArrayList<String> recipientIds;
    private boolean isRead;
    private boolean archived;

    public MessageData(Message message){
        this.id = message.getId();
        this.messageContent = message.getMessageContent();
        this.senderId = message.getSenderId();
        this.recipientIds = message.getRecipientIds();
        this.isRead = message.isRead();
        this.archived = message.isArchived();
    }

    public String getId() {
        return id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public String getSenderId() {
        return senderId;
    }

    public ArrayList<String> getRecipientIds() {
        return recipientIds;
    }

    public boolean isRead() {
        return isRead;
    }

    public boolean isArchived() {
        return archived;
    }
}
