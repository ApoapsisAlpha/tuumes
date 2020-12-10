package group0153.conferencesystem.application.user;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.user.data.UserContactData;
import group0153.conferencesystem.exceptions.UserNotFoundException;
import group0153.conferencesystem.entities.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
     * @param userId The id of the user to get the contacts of.
     * @return An ArrayList of the contacts.
     * @throws UserNotFoundException Thrown if the userId is not found.
     */
    public List<Data> getUserContactsById(String userId) throws UserNotFoundException {
        Optional<User> userPresent = userPersistencePort.findById(userId);
        if (!userPresent.isPresent())
            throw new UserNotFoundException(userId);

        HashSet<String> contactsSet = userPresent.get().getContacts();
        List<Data> contacts = new ArrayList<>();
        for (String contactId : contactsSet) {
            Optional<User> curContactOpt = userPersistencePort.findById(userId);
            if (!curContactOpt.isPresent())
                throw new UserNotFoundException(contactId);
            User curContact = curContactOpt.get();
            contacts.add(new UserContactData(curContact.getId(), curContact.getName(), curContact.getEmail()));
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
        Optional<User> userPresent = userPersistencePort.findById(userId);
        if (!userPresent.isPresent())
            throw new UserNotFoundException(userId);
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
        Optional<User> userPresent = userPersistencePort.findById(userId);
        if (!userPresent.isPresent())
            throw new UserNotFoundException(userId);
        userPersistencePort.addContactById(contactId, userId);
    }
}