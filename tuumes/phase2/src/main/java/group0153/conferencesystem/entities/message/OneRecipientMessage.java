package group0153.conferencesystem.entities.message;

import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;

public class OneRecipientMessage extends Message {
    private String recipientId;

    /**
     * Constructor that instantiates a OneRecipientMessage instance.
     * @param id             : id of the message
     * @param messageContent : String with the message's content
     * @param senderId       : id of sender
     * @param recipientId    : id of recipient
     */
    public OneRecipientMessage(String id, String messageContent, String senderId, String recipientId) {
        super(id, messageContent, senderId);
        this.recipientId = recipientId;
    }

    /**
     * Getter for recipient id
     * @return Arraylist of recipient id
     */
    public String getRecipientId() {
        return recipientId;
    }

    /**
     * Getter for recipient id
     * @return Arraylist of recipient id
     */
    public ArrayList<String> getRecipientIds() {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(recipientId);
        return temp;
    }
}
