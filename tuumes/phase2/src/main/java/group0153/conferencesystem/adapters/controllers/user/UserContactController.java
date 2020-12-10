package group0153.conferencesystem.adapters.controllers.user;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.adapters.controllers.user.resources.UserContactResource;
import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.user.UserContactManager;
import group0153.conferencesystem.exceptions.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/user/contacts")
public class UserContactController {
    private final UserContactManager userContactManager;

    public UserContactController(UserContactManager userContactManager) {
        this.userContactManager = userContactManager;
    }

    @GetMapping("/view")
    public ResponseEntity<Response> getUserContacts(@RequestParam(value = "userId") String userId) {
        try {
            List<Data> contactData = userContactManager.getUserContactsById(userId);
            ResponseArray response = new ResponseArray(true, contactData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<Response> removeContact(@RequestBody UserContactResource contactResource) {
        try {
            userContactManager.removeContactById(contactResource.getContactId(), contactResource.getUserId());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addContact(@RequestBody UserContactResource contactResource) {
        try {
            userContactManager.addContactById(contactResource.getContactId(), contactResource.getUserId());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        }
    }
}
