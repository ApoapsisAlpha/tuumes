package group0153.conferencesystem.application.user;

import group0153.conferencesystem.entities.user.User;
import group0153.conferencesystem.entities.user.UserType;
import group0153.conferencesystem.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static group0153.conferencesystem.entities.user.UserType.VIP;

/**
 * A user use case class that gets a user's events, also adds an event to and removes event from a user's events.
 */

@Component
public class UserEventsManager {
    final UserPersistencePort userPersistencePort;

    /**
     * Instantiates an UserEventsManager
     *
     * @param userPersistencePort How user information is saved to the database.
     */
    public UserEventsManager(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    /**
     * Gets a list of all the event ids a user is taking part in.
     *
     * @param userId The id of the user to get the events of.
     * @return A list of event ids the user is in.
     * @throws UserNotFoundException Thrown when user id is not a valid user.
     */
    public List<String> getUserEvents(String userId) throws UserNotFoundException {
        User user = this.getUser(userId);
        return new ArrayList<>(user.getEvents());
    }

    /**
     * Adds an event to a list of event ids that a user is taking part in.
     *
     * @param userId The id of the user to get events from .
     * @param eventId The event that will be added the user's events.
     * @throws UserNotFoundException Thrown when user id is not a valid user.
     */
    public void addUserEvents(String userId, String eventId) throws UserNotFoundException {
        User user = this.getUser(userId);
        user.addEvent(eventId);
    }

    /**
     * Removes an event from a list of events that user is taking part in.
     *
     * @param userId The id of the user to get events from.
     * @param eventId The event that will be removed the user's events.
     * @throws UserNotFoundException Thrown when user id is not a valid user.
     */
    public void removeUserEvents(String userId, String eventId) throws UserNotFoundException {
        User user = this.getUser(userId);
        user.removeEvent(eventId);
    }

    /**
     * Returns a user's type.
     *
     * @param userId user id which type is taken from.
     * @return UserType of the user
     * @throws UserNotFoundException Thrown when user id is not a valid user.
     */
    public UserType getUserType(String userId) throws UserNotFoundException {
        return getUser(userId).getType();
    }

    private User getUser(String userId) throws UserNotFoundException {
        Optional<User> userPresent = userPersistencePort.findById(userId);
        if (!userPresent.isPresent())
            throw new UserNotFoundException(userId);
        return userPresent.get();
    }
}
