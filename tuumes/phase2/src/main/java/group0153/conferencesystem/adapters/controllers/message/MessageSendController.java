//package group0153.conferencesystem.adapters.controllers.message;
//
//import group0153.conferencesystem.adapters.controllers.Response;
//import group0153.conferencesystem.adapters.controllers.message.requests.MessageComposeRequest;
//import group0153.conferencesystem.entities.message.Message;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value="/messages")
//public class MessageSendController {
//    private final MessageController messageController;
//
//    public MessageSendController(MessageController messageController) {
//        this.messageController = messageController;
//    }
//
//    /**
//     * API command for users to send messages to other users.
//     */
//    @PostMapping("/send")
//    public ResponseEntity<Response> sendMessage(@RequestBody MessageComposeRequest messageComposeRequest) {
//
//    }
//
//    @PostMapping("/send_event")
//    public ResponseEntity<Response> sendEventMessage(@RequestBody MessageComposeRequest messageComposeRequest) {
//
//    }
//}
