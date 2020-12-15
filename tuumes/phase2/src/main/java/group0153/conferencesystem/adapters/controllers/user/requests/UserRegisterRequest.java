package group0153.conferencesystem.adapters.controllers.user.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the registration of a user by checking if they have inputted their information.
 */
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String type = "ATTENDEE";

    /**
     * Gets email.
     *
     * @return Value of email.
     * @throws InvalidInputException No email inputted.
     */
    public String getEmail() throws InvalidInputException {
        if (email.isEmpty())
            throw new InvalidInputException("email");
        return email.toLowerCase();
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     * @throws InvalidInputException No name inputted.
     */
    public String getName() throws InvalidInputException {
        if (name.isEmpty())
            throw new InvalidInputException("name");
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    /**
     * Gets password.
     *
     * @return Value of password.
     * @throws InvalidInputException No password inputted.
     */
    public String getPassword() throws InvalidInputException {
        if (password.isEmpty())
            throw new InvalidInputException("password");
        return password;
    }

    /**
     * Return the type of the user that is currently registering for an account.
     *
     * @return type of user
     */
    public String getType() {
        return type;
    }
}