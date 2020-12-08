package group0153.conferencesystem.adapters.gateways.user;

import group0153.conferencesystem.application.user.UserPersistencePort;
import group0153.conferencesystem.entities.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    /**
     * Finds a user based on their email.
     *
     * @param email The users email.
     * @return List of users that match the email.
     */
    @Override
    public Optional<User> findUserByEmail(String email) {
        // TODO: 12/3/2020 @david plz fix 
        List<UserModel> usersWithEmail = userRepository.findByEmail(email);
        if (usersWithEmail.isEmpty())
            return Optional.empty();
        else {
            UserModel userModel = usersWithEmail.get(0);
            User user = new User.Builder().name(userModel.getName()).email(userModel.getEmail()).
                    password(userModel.getPassword()).type(userModel.getType()).build();
            return Optional.of(user);
        }
    }

    /**
     * Find a user by its id
     *
     * @param userId the id of the user
     * @return the user as an Optional object
     */
    @Override
    public Optional<User> findById(String userId) {
        Optional<UserModel> possibleUserModel = userRepository.findById(userId);

        if (possibleUserModel.isPresent()) {
            UserModel userModel = possibleUserModel.get();

            User user = new User.Builder().name(userModel.getName()).email(userModel.getEmail()).
                    password(userModel.getPassword()).type(userModel.getType()).build();

            return Optional.of(user);
        }

        return Optional.empty();
    }
}
