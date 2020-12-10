package group0153.conferencesystem.adapters.controllers.user.resources;

import group0153.conferencesystem.exceptions.InvalidInputException;

public class UserRegisterResource {
    private String name;
    private String email;
    private String password;

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
}
