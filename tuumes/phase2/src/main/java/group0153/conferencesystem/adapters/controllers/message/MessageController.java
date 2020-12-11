package group0153.conferencesystem.adapters.controllers.message;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.application.message.MessageCreationManager;
import group0153.conferencesystem.application.message.MessageDataPreparer;
import group0153.conferencesystem.application.message.MessageFinder;
import group0153.conferencesystem.application.message.MessageManager;
import group0153.conferencesystem.application.message.data.MessageData;
import group0153.conferencesystem.application.exceptions.MessageIdNotFoundException;
import group0153.conferencesystem.application.message.exception.NoArchivedMessagesException;
import group0153.conferencesystem.application.message.exception.NoMessagesReceivedException;
import group0153.conferencesystem.application.message.exception.NoUnarchivedMessagesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * A controller class that facilitates the sending, viewing, and manipulating of messages as requested by the user
 */
@RestController
@RequestMapping(value = "/messages")
public class MessageController {
    private final MessageCreationManager messageCreationManager;
    private final MessageFinder messageFinder;
    private final MessageManager messageManager;
    private final MessageDataPreparer messageDataPreparer;

    /**
     * Construct a new instance of MessageController using the provided data
     *
     * @param messageCreationManager Instance of MessageCreationManager that handles the creation of new messages
     * @param messageFinder          Instance of MessageFinder that handles the retrieval of messages
     * @param messageManager         Instance of MessageManager that can manipulate stored messages
     * @param messageDataPreparer    Instance of MessageDataPreparer that can prepare messages' data to be utilized
     */
    public MessageController(MessageCreationManager messageCreationManager, MessageFinder messageFinder,
                             MessageManager messageManager, MessageDataPreparer messageDataPreparer) {
        this.messageCreationManager = messageCreationManager;
        this.messageFinder = messageFinder;
        this.messageManager = messageManager;
        this.messageDataPreparer = messageDataPreparer;
    }

//    @PostMapping("/compose")
//    public ResponseEntity<Response> composeMessage(@RequestBody MessageComposeResource messageComposeResource) {
//        // this method composes a message, this returns a Response();
//        // make sure to follow the chart thing I sent on Discord as to what types of users can message what types
//    }
    // TODO: uncomment or remove?

    /**
     * Facilitate the display of all unarchived messages in the program
     *
     * @param userId the id of the current user
     * @return ResponseEntity containing all the data pertaining to all unarchived messages in the program
     */
    @GetMapping("/view")
    public ResponseEntity<Response> viewMessages(@RequestHeader(value = "userId") String userId) {
        // this method gets all the unarchived messages for a user, returns a ResponseArray();
        try {
            ArrayList<String> msgIds = messageFinder.getUnarchivedMsgsByUser(userId);
            ArrayList<MessageData> messages = messageDataPreparer.createMessageDataArray(msgIds);
            return new ResponseEntity<>(new ResponseArray(true, messages), HttpStatus.OK);
        } catch (NoUnarchivedMessagesException | NoMessagesReceivedException | MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Facilitate the display of all archived messages in the program
     *
     * @param userId the id of the current user
     * @return ResponseEntity containing all the data pertaining to all archived messages in the program
     */
    @GetMapping("/view_archived")
    public ResponseEntity<Response> viewMessagesArchived(@RequestHeader(value = "userId") String userId) {
        // this method gets all the archived messages for a user, returns a ResponseArray();
        try {
            ArrayList<String> msgIds = messageFinder.getArchivedMsgsByUser(userId);
            ArrayList<MessageData> messages = messageDataPreparer.createMessageDataArray(msgIds);
            return new ResponseEntity<>(new ResponseArray(true, messages), HttpStatus.OK);
        } catch (NoArchivedMessagesException | NoMessagesReceivedException | MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Facilitate the display of all unread messages in the program
     *
     * @param userId the id of the current user
     * @return ResponseEntity containing all the data pertaining to all unread messages in the program
     */
    @GetMapping("/view_unread")
    public ResponseEntity<Response> viewMessagesUnread(@RequestHeader(value = "userId") String userId) {
        // this method gets all the unread messages for a user, returns a ResponseArray();
        try {
            ArrayList<String> msgIds = messageFinder.getUnreadMsgsByUser(userId);
            ArrayList<MessageData> messages = messageDataPreparer.createMessageDataArray(msgIds);
            return new ResponseEntity<>(new ResponseArray(true, messages), HttpStatus.OK);
        } catch (NoArchivedMessagesException | NoMessagesReceivedException | MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Set the message specified by msgId as read
     *
     * @param msgId the id of a message specified by the user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/read")
    public ResponseEntity<Response> markMessageRead(@RequestHeader(value = "msgId") String msgId) {
        // marks a message as read, this returns a Response();
        try {
            messageManager.setMsgReadStatusById(msgId, true);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Set the message specified by msgId as unread
     *
     * @param msgId the id of a message specified by the user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/unread")
    public ResponseEntity<Response> markMessageUnread(@RequestHeader(value = "msgId") String msgId) {
        // marks a message as unread, this returns a Response();
        try {
            messageManager.setMsgReadStatusById(msgId, false);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Set message specifies by msgId as deleted
     *
     * @param msgId the id of a message specified by the user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/delete")
    public ResponseEntity<Response> deleteMessage(@RequestHeader(value = "msgId") String msgId) {
        // deletes a message, this returns a Response();
        try {
            messageManager.setDeletedStatusById(msgId, true);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Set the message specified by msgId as archived
     *
     * @param msgId the id of a message specified by the user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/archive")
    public ResponseEntity<Response> archiveMessage(@RequestHeader(value = "msgId") String msgId) {
        // archives a message, this returns a Response();
        try {
            messageManager.setArchivedStatusById(msgId, true);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }

    /**
     * Set the message specified by msgId as unarchived
     *
     * @param msgId the id of a message specified by the user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/unarchive")
    public ResponseEntity<Response> unarchiveMessage(@RequestHeader(value = "msgId") String msgId) {
        // unarchives a message, this returns a Response();
        try {
            messageManager.setArchivedStatusById(msgId, false);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }
}
