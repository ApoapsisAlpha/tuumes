package group0153.conferencesystem.adapters.controllers.message;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.adapters.controllers.message.resource.MessageComposeResource;
import group0153.conferencesystem.adapters.controllers.message.resource.MessageResource;
import group0153.conferencesystem.application.message.MessageCreationManager;
import group0153.conferencesystem.application.message.MessageDataPreparer;
import group0153.conferencesystem.application.message.MessageFinder;
import group0153.conferencesystem.application.message.MessageManager;
import group0153.conferencesystem.application.message.data.MessageData;
import group0153.conferencesystem.application.message.exception.MessageIdNotFoundException;
import group0153.conferencesystem.application.message.exception.NoArchivedMessagesException;
import group0153.conferencesystem.application.message.exception.NoMessagesReceivedException;
import group0153.conferencesystem.application.message.exception.NoUnarchivedMessagesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {
    private final MessageCreationManager messageCreationManager;
    private final MessageFinder messageFinder;
    private final MessageManager messageManager;
    private final MessageDataPreparer messageDataPreparer;

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

    @GetMapping("/view")
    public ResponseEntity<Response> viewMessages(@RequestHeader(value = "userId") String userId) {
        // this method gets all the unarchived messages for a user, returns a ResponseArray();
        try {
            ArrayList<String> msgIds = messageFinder.getUnarchivedMsgsByUser(userId);
            ArrayList<MessageData> messages = messageDataPreparer.createMessageDataArray(msgIds);
            return new ResponseEntity<>(new ResponseArray(true, messages), HttpStatus.OK);
        } catch (NoUnarchivedMessagesException | NoMessagesReceivedException | MessageIdNotFoundException e){
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/view_archived")
    public ResponseEntity<Response> viewMessagesArchived(@RequestHeader(value = "userId") String userId) {
        // this method gets all the archived messages for a user, returns a ResponseArray();
        try {
            ArrayList<String> msgIds = messageFinder.getArchivedMsgsByUser(userId);
            ArrayList<MessageData> messages = messageDataPreparer.createMessageDataArray(msgIds);
            return new ResponseEntity<>(new ResponseArray(true, messages), HttpStatus.OK);
        } catch (NoArchivedMessagesException | NoMessagesReceivedException | MessageIdNotFoundException e){
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }

//    @PostMapping("/read")
//    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
//        // marks a message as read, this returns a Response();
//    }
//
//    @PostMapping("/unread")
//    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
//        // marks a message as unread, this returns a Response();
//    }
//
//    @PostMapping("/delete")
//    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
//        // deletes a message, this returns a Response();
//    }
//
//    @PostMapping("/archive")
//    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
//        // archives a message, this returns a Response();
//    }
//
//    @PostMapping("/unarchive")
//    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
//        // unarchives a message, this returns a Response();
//    }
}
