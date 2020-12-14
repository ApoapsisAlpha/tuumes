package group0153.conferencesystem.application.message;

import group0153.conferencesystem.application.event.EventPersistencePort;
import group0153.conferencesystem.application.exceptions.EventNotFoundException;
import group0153.conferencesystem.application.exceptions.InvalidInputException;
import group0153.conferencesystem.application.exceptions.MissingPermissionException;
import group0153.conferencesystem.application.exceptions.UserNotFoundException;
import group0153.conferencesystem.application.user.UserPersistencePort;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.entities.message.Message;
import group0153.conferencesystem.entities.user.User;
import group0153.conferencesystem.entities.user.UserType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * A message use case class which is in charge of creating new messages.
 */
@Component
public class MessageSender {
    private final MessagePersistencePort messagePersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final EventPersistencePort eventPersistencePort;

    /**
     * Precondition: Sender and Recipient are contacts
     * Constructor to instantiate a MessageCreationManager using message repository
     *
     * @param messagePersistencePort where messages are stored
     * @param userPersistencePort    where users are stored
     * @param eventPersistencePort   where events are stored
     */
    public MessageSender(MessagePersistencePort messagePersistencePort,
                         UserPersistencePort userPersistencePort, EventPersistencePort eventPersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.eventPersistencePort = eventPersistencePort;
    }

    /**
     * Create a message with the provided parameters
     *
     * @param messageContent: String with the message's content
     * @param senderId:       id of sender
     * @param recipientIds:   an ArrayList of the recipient(s)'s id(s)
     * @return the id of the message created
     */
    public String create(String messageContent, String senderId, ArrayList<String> recipientIds) {
        String newId = UUID.randomUUID().toString();
        Message message = new Message(newId, messageContent, senderId, recipientIds);
        messagePersistencePort.saveMessage(message);
        return message.getId();
    }

    /**
     * Create a message with the provided parameters
     *
     * @param messageContent: String with the message's content
     * @param senderId:       id of sender
     * @param recipientEmail: a recipient's email
     * @throws UserNotFoundException if given user is not found
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

    /**
     * Create a message with the provided parameters
     *
     * @param messageContent: String with the message's content
     * @param senderId:       id of sender
     * @param eventId:        an event id
     * @throws EventNotFoundException if given event is not found
     */
    public void sendToEvent(String messageContent, String senderId, String eventId) throws EventNotFoundException {
        Optional<Event> eventPresent = eventPersistencePort.findById(eventId);
        if (!eventPresent.isPresent())
            throw new EventNotFoundException(eventId);

        String newId = UUID.randomUUID().toString();
        List<String> attendees = eventPresent.get().getUserIds();
        ArrayList<String> recipients = new ArrayList<>();
        for (String id : attendees) {
            Optional<User> userPresent = userPersistencePort.findById(id);
            if (!userPresent.isPresent())
                throw new UserNotFoundException(id);

            if (userPresent.get().getType() != UserType.SPEAKER) {
                recipients.add(id);
            }
        }
        Message message = new Message(newId, messageContent, senderId, recipients);
        messagePersistencePort.saveMessage(message);
    }

    /**
     * Create a message with the provided parameters
     *
     * @param messageContent: String with the message's content
     * @param senderId:       id of sender
     * @param eventIds:       a list of event ids
     * @throws EventNotFoundException if given event is not found
     */
    public void sendToMultiEvent(String messageContent, String senderId, List<String> eventIds) throws EventNotFoundException {
        for(String id: eventIds){
            this.sendToEvent(messageContent, senderId, id);
        }
    }

    /**
     * Create a message sent to every user
     *
     * @param messageContent: String with message's content
     * @param senderId:       id of sender
     * @throws UserNotFoundException if sender id is not found
     * @throws InvalidInputException if user is not an organizer
     */
    public void sendToEveryone(String messageContent, String senderId) throws UserNotFoundException,
            MissingPermissionException{
        Optional<User> userPresent = userPersistencePort.findById(senderId);
        if (!userPresent.isPresent())
            throw new UserNotFoundException(senderId);

        if (userPresent.get().getType() != UserType.ORGANIZER){
            throw new MissingPermissionException(UserType.ORGANIZER);
        }

        String newId = UUID.randomUUID().toString();
        List<User> users = userPersistencePort.getAllUsers();
        ArrayList<String> recipients = new ArrayList<>();

        for(User user: users){
            recipients.add(user.getId());
        }

        Message message = new Message(newId, messageContent, senderId, recipients);
        messagePersistencePort.saveMessage(message);

    }
}
