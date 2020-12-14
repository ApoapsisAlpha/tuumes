package group0153.conferencesystem.adapters.controllers.message;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.adapters.controllers.message.requests.MessageRequest;
import group0153.conferencesystem.application.exceptions.message.MessageIdNotFoundException;
import group0153.conferencesystem.application.exceptions.message.NoMessagesFoundException;
import group0153.conferencesystem.application.message.MessageDataPreparer;
import group0153.conferencesystem.application.message.MessageFinder;
import group0153.conferencesystem.application.message.MessageManager;
import group0153.conferencesystem.application.message.MessageSender;
import group0153.conferencesystem.application.message.data.MessageData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * A controller class that facilitates the viewing and manipulating of messages as requested by the user.
 */
@RestController
@RequestMapping(value = "/messages")
public class MessageController {
    private final MessageFinder messageFinder;
    private final MessageManager messageManager;
    private final MessageDataPreparer messageDataPreparer;

    /**
     * Construct a new instance of MessageController using the provided data.
     *
     * @param messageFinder          Instance of MessageFinder that handles the retrieval of messages
     * @param messageManager         Instance of MessageManager that can manipulate stored messages
     * @param messageDataPreparer    Instance of MessageDataPreparer that can prepare messages' data to be utilized
     */
    public MessageController(MessageFinder messageFinder, MessageManager messageManager,
                             MessageDataPreparer messageDataPreparer) {
        this.messageFinder = messageFinder;
        this.messageManager = messageManager;
        this.messageDataPreparer = messageDataPreparer;
    }

    /**
     * Facilitate the display of all unarchived messages in the program.
     *
     * @param userId the id of the current user
     * @return ResponseEntity containing all the data pertaining to all unarchived messages in the program
     */
    @GetMapping("/view")
    public ResponseEntity<Response> viewMessages(@RequestParam(value = "userId") String userId) {
        // this method gets all the unarchived messages for a user, returns a ResponseArray();
        try {
            ArrayList<String> msgIds = messageFinder.getUnarchivedMsgsByUser(userId);
            ArrayList<MessageData> messages = messageDataPreparer.createMessageDataArray(msgIds);
            return new ResponseEntity<>(new ResponseArray(true, messages), HttpStatus.OK);
        } catch (NoMessagesFoundException e) {
            return new ResponseEntity<>(new Response(true, "NO_MESSAGES"), HttpStatus.OK);
        }
    }

    /**
     * Facilitate the display of all archived messages in the program.
     *
     * @param userId the id of the current user
     * @return ResponseEntity containing all the data pertaining to all archived messages in the program
     */
    @GetMapping("/view_archived")
    public ResponseEntity<Response> viewMessagesArchived(@RequestParam(value = "userId") String userId) {
        // this method gets all the archived messages for a user, returns a ResponseArray();
        try {
            ArrayList<String> msgIds = messageFinder.getArchivedMsgsByUser(userId);
            ArrayList<MessageData> messages = messageDataPreparer.createMessageDataArray(msgIds);
            return new ResponseEntity<>(new ResponseArray(true, messages), HttpStatus.OK);
        } catch (NoMessagesFoundException e) {
            return new ResponseEntity<>(new Response(true, "NO_MESSAGES"), HttpStatus.OK);
        }
    }

    /**
     * Set the message specified by msgId as read.
     *
     * @param messageRequest instance of MessageRequest containing the details of the message and user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/read")
    public ResponseEntity<Response> markMessageRead(@RequestBody MessageRequest messageRequest) {
        // marks a message as read, this returns a Response();
        try {
            messageManager.setMsgReadStatusById(messageRequest.getMessageId(), messageRequest.getUserId(), true);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_MESSAGE"), HttpStatus.OK);
        }
    }

    /**
     * Set the message specified by msgId as unread.
     *
     * @param messageRequest instance of MessageRequest containing the details of the message and user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/unread")
    public ResponseEntity<Response> markMessageUnread(@RequestBody MessageRequest messageRequest) {
        // marks a message as unread, this returns a Response();
        try {
            messageManager.setMsgReadStatusById(messageRequest.getMessageId(), messageRequest.getUserId(), false);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_MESSAGE"), HttpStatus.OK);
        }
    }

    /**
     * Set message specifies by msgId as deleted.
     *
     * @param messageRequest instance of MessageRequest containing the details of the message and user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/delete")
    public ResponseEntity<Response> deleteMessage(@RequestBody MessageRequest messageRequest) {
        // deletes a message, this returns a Response();
        try {
            messageManager.setDeletedStatusById(messageRequest.getMessageId(), messageRequest.getMessageId(),
                    true);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_MESSAGE"), HttpStatus.OK);
        }
    }

    /**
     * Set the message specified by msgId as archived.
     *
     * @param messageRequest instance of MessageRequest containing the details of the message and user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/archive")
    public ResponseEntity<Response> archiveMessage(@RequestBody MessageRequest messageRequest) {
        // archives a message, this returns a Response();
        try {
            messageManager.setArchivedStatusById(messageRequest.getMessageId(), messageRequest.getMessageId(),
                    true);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_MESSAGE"), HttpStatus.OK);
        }
    }

    /**
     * Set the message specified by msgId as unarchived.
     *
     * @param messageRequest instance of MessageRequest containing the details of the message and user
     * @return ResponseEntity containing whether this operation was a success, possibly along with an explanation
     */
    @PostMapping("/unarchive")
    public ResponseEntity<Response> unarchiveMessage(@RequestBody MessageRequest messageRequest) {
        // unarchives a message, this returns a Response();
        try {
            messageManager.setArchivedStatusById(messageRequest.getMessageId(), messageRequest.getMessageId(),
                    false);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_MESSAGE"), HttpStatus.OK);
        }
    }
}
