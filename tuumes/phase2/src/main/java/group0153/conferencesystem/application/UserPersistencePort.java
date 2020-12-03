package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.User;

public interface UserPersistencePort {
    void saveUser(User user);
}