package group0153.conferencesystem.application.message;

import group0153.conferencesystem.application.message.data.MessageData;
import group0153.conferencesystem.application.message.exception.MessageIdNotFoundException;
import group0153.conferencesystem.entities.message.Message;

import java.util.ArrayList;

/**
 * A message use case class that creates array lists of MessageData representing the
 * list of messages
 */

public class MessageDataPreparer {
    private final MessagePersistencePort messagePersistencePort;

    /**
     * Instantiates an MessageDataPreparer.
     * @param messagePersistencePort How the messages are saved to the database.
     */
    public MessageDataPreparer(MessagePersistencePort messagePersistencePort){
        this.messagePersistencePort = messagePersistencePort;
    }

    /**
     * Creates an array list of MessageData from an array list of Message ids.
     *
     * @param msgIds An array list of the message ids that need to turned into MessageData objects
     * @return An arraylist of MessageData objects corresponding to the ids given
     * @throws MessageIdNotFoundException If message doesn't exist
     */
    public ArrayList<MessageData> createMessageDataArray(ArrayList<String> msgIds){
        ArrayList<MessageData> messageDataArray = new ArrayList<>();

        for(String id: msgIds){
            if (!messagePersistencePort.findById(id).isPresent()){
                throw new MessageIdNotFoundException();
            }
            Message message = messagePersistencePort.findById(id).get();
            MessageData messageData = new MessageData(message);
            messageDataArray.add(messageData);
        }
        return messageDataArray;
    }

//    public MessageData createMessageDataFromId(String msgId){
//        if (!messagePersistencePort.findById(msgId).isPresent()){
//            throw new MessageIdNotFoundException();
//        }
//
//    }

}
