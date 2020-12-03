package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.Message;

public interface MessagePersistencePort {

    void saveMessage(Message message);

}
