package group0153.conferencesystem.adapters.controllers.user;

import group0153.conferencesystem.exceptions.InvalidInputException;

public class UserRegisterResource {
    private String name;
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
        return email.toLowerCase();
    }

    /**
     * Gets name.
     *
     * @return Value of name.
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
     */
    public String getPassword() throws InvalidInputException {
        if (password.isEmpty())
            throw new InvalidInputException("password");
        return password;
    }
}
