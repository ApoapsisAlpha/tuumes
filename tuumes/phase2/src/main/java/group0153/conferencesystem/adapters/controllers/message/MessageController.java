package group0153.conferencesystem.adapters.controllers.message;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.adapters.controllers.message.requests.MessageRequest;
import group0153.conferencesystem.application.exceptions.UserNotFoundException;
import group0153.conferencesystem.application.exceptions.message.MessageIdNotFoundException;
import group0153.conferencesystem.application.message.MessageFinder;
import group0153.conferencesystem.application.message.MessageManager;
import group0153.conferencesystem.application.message.data.MessageData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A controller class that facilitates the viewing and manipulating of messages as requested by the user.
 */
@RestController
@RequestMapping(value = "/api/messages")
public class MessageController {
    private final MessageFinder messageFinder;
    private final MessageManager messageManager;

    /**
     * Construct a new instance of MessageController using the provided data.
     *
     * @param messageFinder          Instance of MessageFinder that handles the retrieval of messages
     * @param messageManager         Instance of MessageManager that can manipulate stored messages
     */
    public MessageController(MessageFinder messageFinder, MessageManager messageManager) {
        this.messageFinder = messageFinder;
        this.messageManager = messageManager;
    }

    /**
     * Fetches all messages for a specific user.
     *
     * @param userId the id of the user
     * @return ResponseEntity containing message data.
     */
    @GetMapping("/view")
    public ResponseEntity<Response> viewMessages(@RequestParam(value = "userId") String userId) {
        try {
            List<MessageData> messages = messageFinder.getMessages(userId);
            return new ResponseEntity<>(new ResponseArray(true, messages), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
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
        try {
            List<MessageData> messages = messageFinder.getArchivedMessages(userId);
            return new ResponseEntity<>(new ResponseArray(true, messages), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
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
        try {
            messageManager.setArchivedStatusById(messageRequest.getMessageId(), messageRequest.getMessageId(),
                    false);
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (MessageIdNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_MESSAGE"), HttpStatus.OK);
        }
    }
}
