//package group0153.conferencesystem.application.message;
//
//import group0153.conferencesystem.application.message.exception.MessageIdNotFoundException;
//import group0153.conferencesystem.application.message.exception.NoMessagesReceivedException;
//import group0153.conferencesystem.application.message.exception.NoMessagesSentException;
//import group0153.conferencesystem.entities.message.Message;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
///**
// * MessageManager class is for the purposes of finding messages and changing message variables.
// */
//
//@Component
//public class MessageManager {
//    final MessagePersistencePort messagePersistencePort;
//
//    /**
//     * Instantiates a MessageManager.
//     *
//     * @param messagePersistencePort How the messages are saved to the database.
//     */
//    public MessageManager(MessagePersistencePort messagePersistencePort){
//        this.messagePersistencePort = messagePersistencePort;
//    }
//
//    /**
//     * Given a message's id, return the message's read state.
//     *
//     * @param msgId the message id
//     * @return read boolean, true meaning the message has been read
//     * @throws MessageIdNotFoundException Thrown if message id doesn't exist
//     */
//    public boolean getMsgReadStatusById(String msgId){
//        Optional<Message> message = messagePersistencePort.findById(msgId);
//        if(!message.isPresent())
//            throw new MessageIdNotFoundException();
//
//        return message.get().isRead();
//    }
//
//    /**
//     * Given a message's id, set the message's read state.
//     *
//     * @param msgId the message id
//     * @param read boolean state of message
//     * @return read boolean, true meaning the message has been read
//     * @throws MessageIdNotFoundException Thrown if message id doesn't exist
//     */
//    public void setMsgReadStatusById(String msgId, boolean read){
//        Optional<Message> message = messagePersistencePort.findById(msgId);
//        if(!message.isPresent())
//            throw new MessageIdNotFoundException();
//
//        message.get().setRead(read);
//    }
//
//    /**
//     * Given a message's id, return the message's content.
//     *
//     * @param msgId
//     * @return message content
//     * @throws MessageIdNotFoundException Thrown if message doesn't exist
//     */
//    public String getMsgContentById(String msgId){
//        Optional<Message> message = messagePersistencePort.findById(msgId);
//        if(!message.isPresent())
//            throw new MessageIdNotFoundException();
//
//        return message.get().getMessageContent();
//    }
//
//
//    /**
//     * Given a message's id, return the sender's id.
//     *
//     * @param msgId
//     * @return sender id
//     * @throws MessageIdNotFoundException Thrown if message doesn't exist
//     */
//    public String getSenderIdByMsgId(String msgId){
//        Optional<Message> message = messagePersistencePort.findById(msgId);
//        if(!message.isPresent())
//            throw new MessageIdNotFoundException();
//
//        return message.get().getSenderId();
//    }
//
//
//    /**
//     * Given a message's id, return the recipient(s)'s id(s).
//     *
//     * @return A list of recipient id(s)
//     */
//    public ArrayList<String> getRecipientIdsByMsgId(String msgId){
//        Optional<Message> message = messagePersistencePort.findById(msgId);
//        if(!message.isPresent())
//            throw new MessageIdNotFoundException();
//
//        return message.get().getRecipientIds();
//    }
//
//    /**
//     * Given a sender's id, find the id(s)'s of all the messages sent by that user.
//     *
//     * @param sender Sender of the message(s)
//     * @return A list of message ids
//     * @throws NoMessagesSentException No messages have been sent by sender
//     */
//    public ArrayList<String> findMsgIdsBySender(String sender){
//        Optional<ArrayList<String>> msgIds = messagePersistencePort.findMsgsBySender(sender);
//        if(!msgIds.isPresent())
//            throw new NoMessagesSentException();
//        return msgIds.get();
//    }
//
//    /**
//     * Given a recipient's id, find the id(s)'s of all the messages sent to that user.
//     *
//     * @param recipient recipient of the message(s)
//     * @return A list of message ids
//     * @throws NoMessagesReceivedException No messages have been received by recipient
//     */
//    public ArrayList<String> findMsgIdsByRecipient(String recipient){
//        Optional<ArrayList<String>> msgIds = messagePersistencePort.findMsgsToRecipient(recipient);
//        if(!msgIds.isPresent())
//            throw new NoMessagesReceivedException();
//        return msgIds.get();
//    }
//
//
//}
