//package group0153.conferencesystem.application.user;
//
//import group0153.conferencesystem.application.user.exception.IncorrectLoginException;
//import group0153.conferencesystem.application.user.exception.InvalidInputException;
//import group0153.conferencesystem.entities.user.User;
//import group0153.conferencesystem.entities.user.UserType;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class UserAuthManager {
//    UserPersistencePort userPresistencePort;
//
//    public UserAuthManager(UserPersistencePort userPresistencePort) {
//        this.userPresistencePort = userPresistencePort;
//    }
//
//    /**
//     * Handle user login, if either field is incorrect throw and error.
//     *
//     * @param email User email
//     * @param password User password
//     * @return The logged in user
//     * @throws IncorrectLoginException Upon incorrect email or password
//     */
//    public User login(String email, String password) throws IncorrectLoginException {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        if (!userOptional.isPresent())
//            throw new IncorrectLoginException("Incorrect email");
//        User user = userOptional.get();
//        if (user.isPasswordValid(password))
//            return user;
//        else
//            throw new IncorrectLoginException("Incorrect password");
//    }
//
//    /**
//     * Create a user with the provided parameters and return its id.
//     *
//     * @param name the name of the user
//     * @param email the email of the user
//     * @param password the password of the user
//     * @param userType the type of user
//     * @return The id of the new constructed user
//     */
//    public String create(String name, String email, String password, UserType userType) throws InvalidInputException {
//        // Don't trim passwords!
//        name = name.trim();
//        email = email.trim();
//        if (name.isEmpty())
//            throw new InvalidInputException("name");
//        else if (email.isEmpty())
//            throw new InvalidInputException("email");
//        else if (password.isEmpty())
//            throw new InvalidInputException("password");
//        else if (userType == null)
//            throw new InvalidInputException("user type");
//
//        User user = new User.Builder().name(name).email(email).password(password).type(userType).build();
//        userRepository.add(user);
//        return user.getId();
//    }
//}