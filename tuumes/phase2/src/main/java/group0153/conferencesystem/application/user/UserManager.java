package group0153.conferencesystem.application.user;

import group0153.conferencesystem.application.user.data.UserContactData;
import group0153.conferencesystem.entities.user.UserType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A user use case class that handles general user interaction.
 */
@Component
public class UserManager {

    private final UserPersistencePort userPersistencePort;

    /**
     * Construct a user manager instance
     * @param userPersistencePort UserPersistencePort instance
     */
    public UserManager(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    /**
     * Get all speakers.
     * @return a list of speaker data
     */
    public List<UserContactData> getSpeakers() {
        return userPersistencePort.getAllUsers().stream().filter(u -> u.getType() == UserType.SPEAKER).map(speaker -> new UserContactData(speaker.getId(), speaker.getName(), speaker.getEmail())).collect(Collectors.toList());
    }

}
