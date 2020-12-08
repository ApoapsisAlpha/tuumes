package group0153.conferencesystem.application.user;

import group0153.conferencesystem.application.user.exception.UserNotFoundException;
import group0153.conferencesystem.entities.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserContactManager {
    final UserPersistencePort userPersistencePort;

    public UserContactManager(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    /**
     * Adds the idOfContact of the User to the contact list of the User corresponding to userId.
     *
     * @param userId      the id of the User whose contact list is to be added to
     * @param idOfContact the id of the User to add to the list
     * @return a boolean whether the idOfContact has been successfully added
     */
    public boolean addUserToContactsById(String userId, String idOfContact) {
        Optional<User> requestedUser = userPersistencePort.findById(userId);
        return requestedUser.map(user -> user.addContact(idOfContact)).orElse(false);
    }

    /**
     * Removes the idOfContact of the User from the contact list of the User corresponding to userId.
     *
     * @param userId      the id of the User whose contact list is to be removed from
     * @param idOfContact the id of the User to remove from the list
     * @return a boolean whether the userId has been successfully removed
     */
    public boolean removeUserFromContactsById(String userId, String idOfContact) {
        Optional<User> requestedUser = userPersistencePort.findById(userId);
        return requestedUser.map(user -> user.removeContact(idOfContact)).orElse(false);
    }

    /**
     * Retrieves the contacts of the given User
     *
     * @param userId id of user
     * @return Returns list of the contacts of the user
     * @throws UserNotFoundException exception if given id doesn't exist
     */
    public List<User> getUserContacts(String userId) throws UserNotFoundException {
        Optional<User> user = userPersistencePort.findById(userId);
        if (!user.isPresent())
            throw new UserNotFoundException(String.format("A user with id %s was not found.", userId));

        List<User> contacts = new ArrayList<>();
        for (String contactId : user.get().getContacts()) {
            Optional<User> contact = userPersistencePort.findById(contactId);
            contact.ifPresent(contacts::add);
        }

        return contacts;
    }

    /**
     * Retrieves the contacts of the given User
     *
     * @param userId The id of the given user
     * @return The list of ids of user's contacts
     * @throws UserNotFoundException exception if given user doesn't exist
     */
    public List<String> getUserContactIds(String userId) throws UserNotFoundException {
        Optional<User> user = userPersistencePort.findById(userId);
        if (!user.isPresent())
            throw new UserNotFoundException(String.format("A user with id %s was not found.", userId));

        List<String> contacts = new ArrayList<>();
        for (String contactId : user.get().getContacts()) {
            Optional<User> contact = userPersistencePort.findById(contactId);
            contact.ifPresent(contactUser -> contacts.add(contactUser.getId()));
        }

        return contacts;
    }
}