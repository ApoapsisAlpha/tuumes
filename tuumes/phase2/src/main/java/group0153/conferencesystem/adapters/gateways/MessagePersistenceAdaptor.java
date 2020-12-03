package group0153.conferencesystem.adapters.gateways;

import group0153.conferencesystem.application.MessagePersistencePort;
import group0153.conferencesystem.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class MessagePersistenceAdaptor implements MessagePersistencePort {

    private MessageRepository messageRepository;

    public MessagePersistenceAdaptor(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Override
    public void saveMessage(Message message) {
        MessageModel messageModel = new MessageModel(
                message.getId(),
                message.getMessageContent(),
                message.getSenderId(),
                message.getRecipientIds(),
                message.isRead()
        );
        messageRepository.save(messageModel);
    }
}
