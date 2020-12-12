//package group0153.conferencesystem.adapters.controllers.message;
//
//import group0153.conferencesystem.adapters.controllers.Response;
//import group0153.conferencesystem.adapters.controllers.message.requests.MessageComposeEventRequest;
//import group0153.conferencesystem.adapters.controllers.message.requests.MessageComposeMultiEventRequest;
//import group0153.conferencesystem.adapters.controllers.message.requests.MessageComposeRequest;
//import group0153.conferencesystem.application.exceptions.EventNotFoundException;
//import group0153.conferencesystem.application.exceptions.InvalidInputException;
//import group0153.conferencesystem.application.exceptions.UserNotFoundException;
//import group0153.conferencesystem.application.message.MessageCreationManager;
//import group0153.conferencesystem.application.user.UserContactManager;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * A controller class that facilitates the sending of messages as requested by the user.
// */
//@RestController
//@RequestMapping(value = "/messages")
//public class MessageSendController {
//    private final MessageCreationManager messageCreationManager;
//    private final UserContactManager userContactManager;
//
//    /**
//     * Construct an instance of MessageSendController using the provided managers.
//     *
//     * @param messageCreationManager instance of MessageCreationManager that can facilitate message creation
//     * @param userContactManager     instance of UserContactManager that can facility manipulation of contacts of a user
//     */
//    public MessageSendController(MessageCreationManager messageCreationManager, UserContactManager userContactManager) {
//        this.messageCreationManager = messageCreationManager;
//        this.userContactManager = userContactManager;
//    }
//
//    /**
//     * API command for users to send messages to other users.
//     *
//     * @param messageComposeRequest instance of MessageComposeRequest containing the details of the message to be sent
//     * @return ResponseEntity containing a Response with status and validity
//     */
//    @PostMapping("/send")
//    public ResponseEntity<Response> sendMessage(@RequestBody MessageComposeRequest messageComposeRequest) {
//        try {
//            messageCreationManager.create(messageComposeRequest.getContent(), messageComposeRequest.getUserId(),
//                    messageComposeRequest.getRecipientEmail());
//            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
//        } catch (UserNotFoundException e) {
//            return new ResponseEntity<>(new Response(false, "Recipient email not valid"), HttpStatus.OK);
//        } catch (InvalidInputException e) {
//            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }
//
//    /**
//     * API command for users to send messages to attendees of an event.
//     *
//     * @param messageComposeEventRequest instance of MessageComposeEventRequest containing the details of the message
//     *                                   to be sent to the attendees of a specified event
//     * @return ResponseEntity containing a Response with status and validity
//     */
//    @PostMapping("/send_event")
//    public ResponseEntity<Response> sendEventMessage(@RequestBody MessageComposeEventRequest messageComposeEventRequest) {
//        try {
//            messageCreationManager.sendToEvent(messageComposeEventRequest.getContent(),
//                    messageComposeEventRequest.getUserId(),
//                    messageComposeEventRequest.getEventId());
//            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
//        } catch (EventNotFoundException e) {
//            return new ResponseEntity<>(new Response(false, "Event id not valid"), HttpStatus.OK);
//        } catch (InvalidInputException e) {
//            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }
//
//    /**
//     * API command for users to send messages to attendees of multiple chosen events.
//     *
//     * @param messageComposeMultiEventRequest instance of MessageComposeMultiEventRequest containing the details of the
//     *                                        message to be sent to the attendees of specified events
//     * @return ResponseEntity containing a Response with status and validity
//     */
//    @PostMapping("/send_multi_event")
//    public ResponseEntity<Response> sendMultiEventMessage(@RequestBody MessageComposeMultiEventRequest messageComposeMultiEventRequest) {
//        try {
//            messageCreationManager.sendToMultiEvent(messageComposeMultiEventRequest.getContent(),
//                    messageComposeMultiEventRequest.getUserId(),
//                    messageComposeMultiEventRequest.getEventIds());
//            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
//        } catch (EventNotFoundException e) {
//            return new ResponseEntity<>(new Response(false, "Event id not valid"), HttpStatus.OK);
//        } catch (InvalidInputException e) {
//            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }
//}
