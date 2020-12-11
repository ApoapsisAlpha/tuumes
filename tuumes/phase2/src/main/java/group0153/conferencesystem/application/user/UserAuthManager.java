package group0153.conferencesystem.application.user;

import group0153.conferencesystem.application.user.data.UserLoginData;
import group0153.conferencesystem.application.user.exception.IncorrectLoginException;
import group0153.conferencesystem.application.user.exception.UserExistsException;
import group0153.conferencesystem.entities.user.User;
import group0153.conferencesystem.entities.user.UserType;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * A user use case class to handle login and the creation of new users.
 */

@Component
public class UserAuthManager {
    UserPersistencePort userPersistencePort;

    /**
     * Instantiates an UserAuthManager
     * @param userPersistencePort How users are saved to the database
     */
    public UserAuthManager(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    /**
     * Handle user login, if either field is incorrect throw and error.
     *
     * @param email User email
     * @param password User password
     * @return The logged in user
     * @throws IncorrectLoginException Upon incorrect email or password
     */
    public UserLoginData login(String email, String password) throws IncorrectLoginException {
        Optional<User> userOptional = userPersistencePort.findUserByEmail(email);
        if (!userOptional.isPresent())
            throw new IncorrectLoginException("email");
        User user = userOptional.get();
        if (user.isPasswordValid(password))
            return new UserLoginData(user.getId(), user.getType());
        else
            throw new IncorrectLoginException("password");
    }

    /**
     * Create a user with the provided parameters and return its id.
     * Precondition: Email, password, and userType are valid and not empty.
     *
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @param userType the type of user
     * @throws UserExistsException if the user already exists
     */
    public void create(String name, String email, String password, UserType userType) throws
            UserExistsException {
        Optional<User> userExists = userPersistencePort.findUserByEmail(email);
        if (userExists.isPresent())
            throw new UserExistsException();

        String id = UUID.randomUUID().toString();
        User user = new User.Builder().id(id).name(name).email(email).password(password).type(userType).build();
        userPersistencePort.saveUser(user);
    }
}