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

/**
 * A class facilitating the user's interactions with their contacts
 */
@RestController
@RequestMapping(value = "/user/contacts")
public class UserContactController {
    private final UserContactManager userContactManager;

    /**
     * Construct a new instance of UserContactController with the specified userContactManager
     *
     * @param userContactManager an instance of UserContactManager to be used to manage contacts
     */
    public UserContactController(UserContactManager userContactManager) {
        this.userContactManager = userContactManager;
    }

    /**
     * Get and display the user's contacts via a presenter and UI
     *
     * @param userId the String id of the current user
     * @return Response entity with the data to be displayed and a status
     */
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

    /**
     * Attempt to remove a contact specified by the user from their contacts and display the result via a presenter and UI
     *
     * @param contactResource Instance of UserContactResource that contains the required contact data
     * @return Response entity with the data to be displayed and a status
     */
    @PostMapping("/remove")
    public ResponseEntity<Response> removeContact(@RequestBody UserContactResource contactResource) {
        try {
            userContactManager.removeContactById(contactResource.getContactId(), contactResource.getUserId());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Attempt to add a contact specified by the user to their contacts and display the result via a presenter and UI
     *
     * @param contactResource Instance of UserContactResource that contains the required contact data
     * @return Response entity with the data to be displayed and a status
     */
    @PostMapping("/add")
    public ResponseEntity<Response> addContact(@RequestBody UserContactResource contactResource) {
        try {
            userContactManager.addContactById(contactResource.getContactId(), contactResource.getUserId());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            if (e.getUserId().equals(contactResource.getContactId()))
                return new ResponseEntity<>(new Response(false, "USER_NOT_FOUND"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        }
    }
}
