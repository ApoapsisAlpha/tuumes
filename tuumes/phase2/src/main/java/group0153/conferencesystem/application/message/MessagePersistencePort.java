package group0153.conferencesystem.application.message;

import group0153.conferencesystem.entities.message.Message;

import java.util.Optional;

public interface MessagePersistencePort {

    void saveMessage(Message message);

    Optional<Message> findById(String msgId);
}
