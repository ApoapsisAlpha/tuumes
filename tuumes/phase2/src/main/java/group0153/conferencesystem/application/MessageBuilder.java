package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.Message;
import group0153.conferencesystem.entities.MultiRecipientMessage;
import group0153.conferencesystem.entities.OneRecipientMessage;

import java.util.ArrayList;

/**
 * A class for building and returning a message.
 * Use the setMessageAttributes method to quickly set the details of the messages.
 * Use the build method to return an instance of the message being built.
 */
public class MessageBuilder {
    private String id;              // id of the message
    private String messageContent;  // string containing the message content
    private String senderId;        // the sender's id
    private ArrayList<String> recipientIds;
    private String recipientId;

    private void setId(String id){
        this.id = id;
    }

    private void setMessageContent(String messageContent){
        this.messageContent = messageContent;
    }

    private void setSenderId(String senderId){
        this.senderId = senderId;
    }

    private void setRecipientIds(ArrayList<String> recipientIds){
        this.recipientIds = recipientIds;
    }

    private void setRecipientId(String recipientId){
        this.recipientId = recipientId;
    }

    /**
     * Set attributes of message specific to multiple recipients
     * @param id The id of the message
     * @param messageContent The String content of the message
     * @param senderId The id of the sender of the message
     * @param recipientIds The ids of the recipients of the message
     */
    public void setMessageAttributes(String id, String messageContent, String senderId, ArrayList<String> recipientIds){
        this.setId(id);
        this.setMessageContent(messageContent);
        this.setSenderId(senderId);
        this.setRecipientIds(recipientIds);
    }

    /**
     * Set attributes of message specific to one recipient
     * @param id The id of the message
     * @param messageContent The String content of the message
     * @param senderId The id of the sender of the message
     * @param recipientId The ids of the recipients of the message
     */
    public void setMessageAttributes(String id, String messageContent, String senderId, String recipientId){
        this.setId(id);
        this.setMessageContent(messageContent);
        this.setSenderId(senderId);
        this.setRecipientId(recipientId);
    }

    /**
     *
     * @param messageType The type of the message to be returned. The type that is entered
     *                    will be checked without case sensitivity.
     *                    Valid types currently are:
     *                    OneRecipientMessage
     *                    MultiRecipientMessage
     * @return Returns null if the messageType given is not a valid messageType.
     * Returns the message comprising of the attributes that are currently set otherwise
     * (the variables can be set by the set methods in this instance).
     */
    public Message build(String messageType){
        if(messageType.equalsIgnoreCase("OneRecipientMessage"))
            return new OneRecipientMessage(id, messageContent, senderId, recipientId);
        if(messageType.equalsIgnoreCase("MultiRecipientMessage"))
            return new MultiRecipientMessage(id, messageContent, senderId, recipientIds);
        return null;
    }
}
