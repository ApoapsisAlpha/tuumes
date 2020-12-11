//package group0153.conferencesystem.adapters.gateways.message;
//
//import group0153.conferencesystem.application.message.MessagePersistencePort;
//import group0153.conferencesystem.entities.message.Message;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//@Component
//public class MessagePersistenceAdaptor implements MessagePersistencePort {
//
//    private MessageRepository messageRepository;
//
//    public MessagePersistenceAdaptor(MessageRepository messageRepository){
//        this.messageRepository = messageRepository;
//    }
//
//    @Override
//    public void saveMessage(Message message) {
//        MessageModel messageModel = new MessageModel(
//                message.getId(),
//                message.getMessageContent(),
//                message.getSenderId(),
//                message.getRecipientIds(),
//                message.isRead()
//        );
//        messageRepository.save(messageModel);
//    }
//
//    @Override
//    public Optional<Message> findById(String msgId) {
//        Optional<MessageModel> possibleMessageModel = messageRepository.findById(msgId);
//        if (possibleMessageModel.isPresent()){
//            MessageModel messageModel = possibleMessageModel.get();
//            Message msg = new Message(messageModel.getResourceId(), messageModel.getMessageContent(),
//                    messageModel.getSenderId(), messageModel.getRecipientIds());
//            return Optional.of(msg);
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public ArrayList<String> getMsgsBySender(String sender) {
//        return Optional.empty();
//    }
//
//    @Override
//    public ArrayList<String> getMsgsToRecipient(String recipient) {
//        return Optional.empty();
//    }
//}
