package group0153.conferencesystem.adapters.controllers.message;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.message.requests.MessageComposeEventRequest;
import group0153.conferencesystem.adapters.controllers.message.requests.MessageComposeMultiEventRequest;
import group0153.conferencesystem.adapters.controllers.message.requests.MessageComposeRequest;
import group0153.conferencesystem.application.exceptions.EventNotFoundException;
import group0153.conferencesystem.application.exceptions.InvalidInputException;
import group0153.conferencesystem.application.exceptions.UserNotFoundException;
import group0153.conferencesystem.application.message.MessageCreationManager;
import group0153.conferencesystem.application.user.UserContactManager;
import group0153.conferencesystem.entities.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/messages")
public class MessageSendController {
    private final MessageCreationManager messageCreationManager;
    private final UserContactManager userContactManager;

    public MessageSendController(MessageCreationManager messageCreationManager, UserContactManager userContactManager) {
        this.messageCreationManager = messageCreationManager;
        this.userContactManager = userContactManager;
    }

    /**
     * API command for users to send messages to other users.
     */
    @PostMapping("/send")
    public ResponseEntity<Response> sendMessage(@RequestBody MessageComposeRequest messageComposeRequest) {
        try {
            messageCreationManager.create(messageComposeRequest.getContent(), messageComposeRequest.getUserId(),
                    messageComposeRequest.getRecipientEmail());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(new Response(false, "Recipient email not valid"), HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/send_event")
    public ResponseEntity<Response> sendEventMessage(@RequestBody MessageComposeEventRequest messageComposeEventRequest) {
        try {
            messageCreationManager.sendToEvent(messageComposeEventRequest.getContent(),
                    messageComposeEventRequest.getUserId(),
                    messageComposeEventRequest.getEventId());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (EventNotFoundException e){
            return new ResponseEntity<>(new Response(false, "Event id not valid"), HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/send_multi_event")
    public ResponseEntity<Response> sendMultiEventMessage(@RequestBody MessageComposeMultiEventRequest messageComposeMultiEventRequest) {
        try {
            messageCreationManager.sendToMultiEvent(messageComposeMultiEventRequest.getContent(),
                    messageComposeMultiEventRequest.getUserId(),
                    messageComposeMultiEventRequest.getEventIds());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (EventNotFoundException e){
            return new ResponseEntity<>(new Response(false, "Event id not valid"), HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
