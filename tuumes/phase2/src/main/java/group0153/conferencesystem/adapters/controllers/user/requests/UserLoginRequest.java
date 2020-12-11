package group0153.conferencesystem.adapters.controllers.user.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the login of a user by checking if they have inputted their login information
 */
public class UserLoginRequest {
    private String email; // TODO: are these two variables necessary? They aren't initialized anywhere
    private String password;

    /**
     * Gets email.
     *
     * @return Value of email.
     * @throws InvalidInputException When the user does not input anything into the email field
     */
    public String getEmail() throws InvalidInputException {
        if (email.isEmpty())
            throw new InvalidInputException("email");
        return email;
    }

    /**
     * Gets password.
     *
     * @return Value of password.
     * @throws InvalidInputException When the user does not input anything into the password field
     */
    public String getPassword() throws InvalidInputException {
        if (password.isEmpty())
            throw new InvalidInputException("password");
        return password;
    }
}
