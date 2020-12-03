package group0153.conferencesystem.application.user;

import group0153.conferencesystem.application.user.exception.IncorrectLoginException;
import group0153.conferencesystem.application.user.exception.InvalidInputException;
import group0153.conferencesystem.entities.user.User;
import group0153.conferencesystem.entities.user.UserType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuthManager {
    UserPersistencePort userPresistencePort;

    public UserAuthManager(UserPersistencePort userPresistencePort) {
        this.userPresistencePort = userPresistencePort;
    }

    /**
     * Handle user login, if either field is incorrect throw and error.
     *
     * @param email User email
     * @param password User password
     * @return The logged in user
     * @throws IncorrectLoginException Upon incorrect email or password
     */
    public User login(String email, String password) throws IncorrectLoginException {
        Optional<User> userOptional = userPresistencePort.findUserByEmail(email);
        if (!userOptional.isPresent())
            throw new IncorrectLoginException("email");
        User user = userOptional.get();
        if (user.isPasswordValid(password))
            return user;
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
     * @return The id of the new constructed user
     */
    public String create(String name, String email, String password, UserType userType) {
        User user = new User.Builder().name(name).email(email).password(password).type(userType).build();
        userRepository.add(user);
        return user.getId();
    }
}