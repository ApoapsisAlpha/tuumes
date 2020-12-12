package group0153.conferencesystem.application.message;

import group0153.conferencesystem.adapters.gateways.user.UserPersistenceAdapter;
import group0153.conferencesystem.application.event.EventPersistencePort;
import group0153.conferencesystem.application.exceptions.EventNotFoundException;
import group0153.conferencesystem.application.exceptions.UserNotFoundException;
import group0153.conferencesystem.application.user.UserPersistencePort;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.entities.message.Message;
import group0153.conferencesystem.entities.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * A message use case class which is in charge of creating new messages.
 */

public class MessageCreationManager {
    private MessagePersistencePort messagePersistencePort;
    private UserPersistencePort userPersistencePort;
    private EventPersistencePort eventPersistencePort;

    /**
     * Precondition: Sender and Recipient are contacts
     * Constructor to instantiate a MessageCreationManager using message repository
     *
     * @param messagePersistencePort where messages are stored
     */
    public MessageCreationManager(MessagePersistencePort messagePersistencePort,
                                  UserPersistencePort userPersistencePort, EventPersistencePort eventPersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     * Create a message with the provided parameters and return its id.
     *
     * @param messageContent: String with the message's content
     * @param senderId: id of sender
     * @param recipientIds: an ArrayList of the recipient(s)'s id(s)
     * @return the id of the message created
     */
    public String create(String messageContent, String senderId, ArrayList<String> recipientIds) {
        String newId = UUID.randomUUID().toString();
        Message message = new Message(newId, messageContent, senderId, recipientIds);
        messagePersistencePort.saveMessage(message);
        return message.getId();
    }

    /**
     * Create a message with the provided parameters and return its id.
     *
     * @param messageContent: String with the message's content
     * @param senderId: id of sender
     * @param recipientEmail: a recipient's email
     */
    public void create(String messageContent, String senderId, String recipientEmail) throws UserNotFoundException {
        Optional<User> userPresent = userPersistencePort.findUserByEmail(recipientEmail);
        if (!userPresent.isPresent())
            throw new UserNotFoundException(recipientEmail);

        String newId = UUID.randomUUID().toString();
        ArrayList<String> recipient = new ArrayList<>();
        recipient.add(userPresent.get().getId());
        Message message = new Message(newId, messageContent, senderId, recipient);
        messagePersistencePort.saveMessage(message);
    }

    public void sendToEvent(String messageContent, String senderId, String eventId){
        Optional<Event> eventPresent = eventPersistencePort.findById(eventId);
        if (!eventPresent.isPresent())
            throw new EventNotFoundException(eventId);

        String newId = UUID.randomUUID().toString();
        List<String> listRecipients = eventPresent.get().getUserIds();
        ArrayList<String> recipients = new ArrayList<>(listRecipients);
        Message message = new Message(newId, messageContent, senderId, recipients);
        messagePersistencePort.saveMessage(message);
    }
}
