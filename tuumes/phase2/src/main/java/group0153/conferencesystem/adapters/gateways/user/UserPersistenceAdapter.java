package group0153.conferencesystem.adapters.gateways.user;

import group0153.conferencesystem.application.user.UserPersistencePort;
import group0153.conferencesystem.entities.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceAdapter implements UserPersistencePort {
    private final UserRepository userRepository;

    /**
     * Constructs an instance of UserPersistenceAdapter using the provided UserRepository instance
     *
     * @param userRepository a repository storing Users
     */
    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Save the provided User instance
     *
     * @param user a User instance that is to be saved
     */
    @Override
    public void saveUser(User user) {
        UserModel userModel = new UserModel(user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                user.getType(), user.getEvents(), user.getContacts(), user.getMessages());
        userRepository.save(userModel);
    }
}
