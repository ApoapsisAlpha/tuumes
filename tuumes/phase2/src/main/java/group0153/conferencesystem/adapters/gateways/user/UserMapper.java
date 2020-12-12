package group0153.conferencesystem.adapters.gateways.user;

import group0153.conferencesystem.entities.user.User;

import java.util.Optional;

/**
 * A class which acts as a go-between for a UserModel instance and its corresponding User instance.
 */
public class UserMapper {

    /**
     * Maps an optional UserModel instance to an optional User entity.
     *
     * @param userModel the UserModel instance
     * @return the mapped entity
     */
    Optional<User> mapModelToEntity(Optional<UserModel> userModel) {
        return userModel.flatMap(u -> {
            User user = new User.Builder()
                    .id(u.getResourceId())
                    .name(u.getName())
                    .email(u.getEmail())
                    .password(u.getPassword())
                    .type(u.getType()).build();

            u.getContacts().forEach(contact -> user.addContact(contact.getResourceId()));
            u.getMessages().forEach(message -> user.addMessage(message.getResourceId()));
            return Optional.of(user);
        });
    }

}
