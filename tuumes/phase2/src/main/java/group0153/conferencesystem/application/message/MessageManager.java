package group0153.conferencesystem.application.message;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * MessageManager class is for the purposes of finding messages and changing message variables.
 */

@Component
public class MessageManager {
    MessagePersistencePort messagePersistencePort;

    /**
     * Instantiates a MessageManager.
     *
     * @param messagePersistencePort How the messages are saved to the database.
     */
    public MessageManager(MessagePersistencePort messagePersistencePort){
        this.messagePersistencePort = messagePersistencePort;
    }

//    /**
//     * Given a sender's id, find the id(s)'s of all the messages sent by that user.
//     *
//     * @param sender Sender of the message(s)
//     * @return A list of message ids
//     */
//    public ArrayList<String> findBySender(String sender){
//
//    }
//
//
//
}
