package group0153.conferencesystem.adapters.controllers.message;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.message.resource.MessageComposeResource;
import group0153.conferencesystem.adapters.controllers.message.resource.MessageResource;
import group0153.conferencesystem.application.message.MessageCreationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {
    private final MessageCreationManager messageCreationManager;

    public MessageController(MessageCreationManager messageCreationManager) {
        this.messageCreationManager = messageCreationManager;
    }

    @PostMapping("/compose")
    public ResponseEntity<Response> composeMessage(@RequestBody MessageComposeResource messageComposeResource) {
        // this method composes a message, this returns a Response();
        // make sure to follow the chart thing I sent on Discord as to what types of users can message what types
    }

    @GetMapping("/view")
    public ResponseEntity<Response> viewMessages(@RequestHeader(value = "userId") String userId) {
        // this method gets all the unarchived messages for a user, returns a ResponseArray();
    }

    @GetMapping("/view_archived")
    public ResponseEntity<Response> viewMessagesArchived(@RequestHeader(value = "userId") String userId) {
        // this method gets all the archived messages for a user, returns a ResponseArray();
    }

    @PostMapping("/read")
    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
        // marks a message as read, this returns a Response();
    }

    @PostMapping("/unread")
    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
        // marks a message as unread, this returns a Response();
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
        // deletes a message, this returns a Response();
    }

    @PostMapping("/archive")
    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
        // archives a message, this returns a Response();
    }

    @PostMapping("/unarchive")
    public ResponseEntity<Response> composeMessage(@RequestBody MessageResource messageResource) {
        // unarchives a message, this returns a Response();
    }
}
