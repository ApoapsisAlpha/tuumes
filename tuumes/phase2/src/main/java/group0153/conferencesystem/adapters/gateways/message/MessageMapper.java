package group0153.conferencesystem.adapters.gateways.message;

import group0153.conferencesystem.adapters.gateways.user.UserModel;
import group0153.conferencesystem.entities.message.Message;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A class which acts as a go-between for a MessageModel instance and its corresponding Message instance.
 */
public class MessageMapper {

    /**
     * Maps an optional MessageModel instance to an optional Message entity.
     *
     * @param messageModel the MessageModel instance
     * @return the mapped entity
     */
    Optional<Message> mapModelToEntity(Optional<MessageModel> messageModel) {
        return messageModel.flatMap(m -> {
            Message message = new Message(m.getResourceId(), m.getMessageContent(), m.getSender().getResourceId(),
                    m.getRecipients().stream().map(UserModel::getResourceId).collect(Collectors.toList()));

            m.getReadSet().forEach(user -> message.addRead(user.getResourceId()));
            m.getArchivedSet().forEach(user -> message.addArchived(user.getResourceId()));
            m.getDeleteSet().forEach(user -> message.addDeleted(user.getResourceId()));
            return Optional.of(message);
        });
    }

}
