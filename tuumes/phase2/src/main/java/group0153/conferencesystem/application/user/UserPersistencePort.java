package group0153.conferencesystem.application.user;

import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.entities.user.User;

import java.util.List;
import java.util.Optional;

/**
 * An interface for saving users to the database. Also describes methods that finds and/or changes the
 * information in the database.
 */

public interface UserPersistencePort {
    /**
     * Saves the user.
     *
     * @param user User object to be saved.
     */
    void saveUser(User user);

    /**
     * Find a user based on their email.
     *
     * @param email The users email.
     * @return The user with the set email. (Empty if they don't exist)
     */
    Optional<User> findUserByEmail(String email);

    /**
     * Find a user based on their id.
     *
     * @param userId The users id.
     * @return The user with the id. (Empty if they don't exist)
     */
    Optional<User> findById(String userId);

    /**
     * Find a user based on an id, and remove a user from their contacts based on the contacts id.
     *
     * @param contactId The id of the contact to remove.
     * @param userId The id of the user to remove the contact from.
     */
    void removeContactById(String contactId, String userId);

    /**
     * Find a user based on an id, and add a user id to their contacts.
     *
     * @param contactId The id of the contact to add.
     * @param userId The id of the user to add the contact to.
     */
    void addContactById(String contactId, String userId);

    /**
     * Gets a list of all users at the conference.
     * @return A list of all users at the conference.
     */
    List<User> getAllUsers();
}