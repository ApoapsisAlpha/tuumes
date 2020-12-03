package group0153.conferencesystem.entities.message;

import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;

public class MultiRecipientMessage extends Message {
    private ArrayList<String> recipientIds;

    /**
     * Constructor that instantiates a MultiRecipientMessage instance.
     * @param id             : id of the message
     * @param messageContent : String with the message's content
     * @param senderId       : id of sender
     * @param recipientIds   : an ArrayList of the recipient(s)'s id(s)
     */
    public MultiRecipientMessage(String id, String messageContent, String senderId, ArrayList<String> recipientIds) {
        super(id, messageContent, senderId);
        this.recipientIds = recipientIds;
    }

    /**
     * Getter for recipient ids
     * @return Arraylist of recipient(s) id(s)
     */
    public ArrayList<String> getRecipientIds() {
        return recipientIds;
    }

}
