package group0153.conferencesystem.application.message;

import group0153.conferencesystem.entities.message.Message;

public interface MessagePersistencePort {

    void saveMessage(Message message);

}
