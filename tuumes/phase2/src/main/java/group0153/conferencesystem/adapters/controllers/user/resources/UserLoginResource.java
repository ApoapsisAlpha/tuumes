package group0153.conferencesystem.adapters.controllers.user.resources;

import group0153.conferencesystem.exceptions.InvalidInputException;

public class UserLoginResource {
    private String email;
    private String password;

    /**
     * Gets email.
     *
     * @return Value of email.
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
     */
    public String getPassword() throws InvalidInputException {
        if (password.isEmpty())
            throw new InvalidInputException("password");
        return password;
    }
}
