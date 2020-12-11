package group0153.conferencesystem.application.user;

import group0153.conferencesystem.entities.user.User;
import group0153.conferencesystem.entities.user.UserType;
import group0153.conferencesystem.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static group0153.conferencesystem.entities.user.UserType.VIP;

@Component
public class UserEventsManager {
    final UserPersistencePort userPersistencePort;

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
        return new ArrayList<String>(user.getEvents());
    }

    public void addUserEvents(String userId, String eventId) throws UserNotFoundException {
        User user = this.getUser(userId);
        user.addEvent(eventId);
    }

    public void removeUserEvents(String userId, String eventId) throws UserNotFoundException {
        User user = this.getUser(userId);
        user.removeEvent(eventId);
    }

    public boolean userIsVip(String userId) throws UserNotFoundException {
        User user = this.getUser(userId);
        return user.getType() == UserType.VIP;
    }

    private User getUser(String userId) throws UserNotFoundException {
        Optional<User> userPresent = userPersistencePort.findById(userId);
        if (!userPresent.isPresent())
            throw new UserNotFoundException(userId);
        return userPresent.get();
    }
}
