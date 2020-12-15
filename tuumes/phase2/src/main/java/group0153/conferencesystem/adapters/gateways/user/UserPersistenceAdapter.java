package group0153.conferencesystem.adapters.gateways.user;

import group0153.conferencesystem.application.user.UserPersistencePort;
import group0153.conferencesystem.entities.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A class facilitating the retrieval and saving of information pertaining to users to the database.
 */
@Component
public class UserPersistenceAdapter implements UserPersistencePort {
    private final UserRepository userRepository;

    /**
     * Constructs an instance of UserPersistenceAdapter using the provided UserRepository instance.
     *
     * @param userRepository a repository storing Users
     */
    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Save the provided User instance.
     *
     * @param user a User instance that is to be saved
     */
    @Override
    public void saveUser(User user) {
        UserModel userModel = new UserModel(user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                user.getType());
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
        Optional<UserModel> userModel = userRepository.findByEmail(email);
        return new UserMapper().mapModelToEntity(userModel);
    }

    /**
     * Find a user based on their id.
     *
     * @param userId The user's id.
     * @return The user with the id. (Empty if they don't exist)
     */
    @Override
    public Optional<User> findById(String userId) {
        Optional<UserModel> userModel = userRepository.findByResourceId(userId);
        return new UserMapper().mapModelToEntity(userModel);
    }

    /**
     * Find a user based on an id, and remove a user from their contacts based on the contacts id.
     *
     * @param contactId The id of the contact to remove.
     * @param userId    The id of the user to remove the contact from.
     */
    @Override
    public void removeContactById(String contactId, String userId) {
        UserModel userModel = userRepository.findByResourceId(userId).get();
        UserModel contactModel = userRepository.findByResourceId(contactId).get();
        userModel.getContacts().remove(contactModel);
        userRepository.flush();
    }

    /**
     * Find a user based on an id, and add a user id to their contacts.
     *
     * @param contactId The id of the contact to add.
     * @param userId    The id of the user to add the contact to.
     */
    @Override
    public void addContactById(String contactId, String userId) {
        UserModel userModel = userRepository.findByResourceId(userId).get();
        UserModel contactModel = userRepository.findByResourceId(contactId).get();
        userModel.getContacts().add(contactModel);
        userRepository.flush();
    }

    /**
     * Gets a list of all users at the conference.
     * @return A list of all users at the conference.
     */
    @Override
    public List<User> getAllUsers() {
        UserMapper mapper = new UserMapper();
        return userRepository.findAll().stream()
                .map(userModel -> mapper.mapModelToEntity(Optional.of(userModel)).get())
                .collect(Collectors.toList());
    }
}
