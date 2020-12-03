package group0153.conferencesystem.application.user;

import group0153.conferencesystem.entities.user.User;

public interface UserPersistencePort {
    void saveUser(User user);
}