package group0153.conferencesystem.application.user;

import group0153.conferencesystem.entities.user.User;

import java.util.Optional;

public interface UserPersistencePort {
    void saveUser(User user);
    Optional<User> findUserByEmail(String email);
}