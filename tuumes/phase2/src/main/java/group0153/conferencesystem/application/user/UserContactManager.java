package group0153.conferencesystem.application.user;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.user.data.UserContactData;
import group0153.conferencesystem.exceptions.UserNotFoundException;
import group0153.conferencesystem.entities.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * A user use case class that handles a user's contacts. This includes getting, adding to, and removing from a user's contacts.
 */

@Component
public class UserContactManager {
    final UserPersistencePort userPersistencePort;

    /**
     * Constructs an instance of UserContactManager using the provided userPersistencePort.
     *
     * @param userPersistencePort the UserPersistencePort through which data can be saved
     */
    public UserContactManager(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    /**
     * Return the list of all the contacts a user has based on id.
     *
     * @param userId The id of the user.
     * @return An ArrayList of the contacts.
     * @throws UserNotFoundException Thrown if the userId is not found.
     */
    public List<Data> getUserContactsById(String userId) throws UserNotFoundException {
        User user = userPersistencePort.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Data> contacts = new ArrayList<>();
        for (String contactId : user.getContacts()) {
            User contact = userPersistencePort.findById(contactId).orElseThrow(() -> new UserNotFoundException(contactId));
            contacts.add(new UserContactData(contact.getId(), contact.getName(), contact.getEmail()));
        }

        return contacts;
    }

    /**
     * Remove a contact from a user based on the id of both of the user and the contact.
     *
     * @param contactId The contacts id.
     * @param userId The user id to remove the contact from.
     * @throws UserNotFoundException Thrown if userId or contactId are not found.
     */
    public void removeContactById(String contactId, String userId) throws UserNotFoundException {
        User user = userPersistencePort.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        if (!user.getContacts().contains(contactId))
            throw new UserNotFoundException(contactId);

        userPersistencePort.removeContactById(contactId, userId);
    }

    /**
     * Add a contact to a user based on the id of both the user and the contact.
     *
     * @param contactId The contacts id.
     * @param userId The user id to add the contact to.
     * @throws UserNotFoundException Thrown if userId or contactId are not found.
     */
    public void addContactById(String contactId, String userId) throws UserNotFoundException {
        userPersistencePort.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userPersistencePort.findById(contactId).orElseThrow(() -> new UserNotFoundException(contactId));
        userPersistencePort.addContactById(contactId, userId);
    }
}