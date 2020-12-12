package group0153.conferencesystem.adapters.controllers.event;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.adapters.controllers.event.requests.EventCreationRequest;
import group0153.conferencesystem.adapters.controllers.event.requests.EventRegistrationRequest;
import group0153.conferencesystem.application.event.*;
import group0153.conferencesystem.application.event.data.EventData;
import group0153.conferencesystem.application.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * A controller class that facilitates the registering, viewing, and manipulating of events as requested by the user.
 */

@RestController
@RequestMapping(value="/events")
public class EventController {

    private EventManager eventManager;

    /**
     * Creates an EventController instance.
     * @param eventManager an instance of eventManager
     */
    public EventController(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Fetches every event that is available for the given user.
     *
     * @param userId The id of the user
     * @return response indicating success/failure along with a list of events in the case of a success
     */
    @GetMapping("/available")
    public ResponseEntity<Response> getAvailableEvents(@RequestParam(value = "userId") String userId) {
        try {
            List<EventData> events = eventManager.getAvailableEvents(userId);
            return new ResponseEntity<>(new ResponseArray(true, events), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Invalid user"), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Registers a user to an event.
     * @param registrationResource request data
     * @return response indicating success/failure
     */
    @PostMapping("/register")
    public ResponseEntity<Response> registerUserForEvent(@RequestBody EventRegistrationRequest registrationResource) {
        try {
            eventManager.registerUserForEvent(registrationResource.getEventId(), registrationResource.getUserId());
            return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Invalid user"), HttpStatus.FORBIDDEN);
        } catch (EventNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Invalid event"), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Unregisters a user from an event.
     * @param registrationResource request data
     * @return response indicating success/failure
     */
    @PostMapping("/unregister")
    public ResponseEntity<Response> unregisterUserFromEvent(@RequestBody EventRegistrationRequest registrationResource) {
        try {
            eventManager.unregisterUserFromEvent(registrationResource.getEventId(), registrationResource.getUserId());
            return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Invalid user"), HttpStatus.FORBIDDEN);
        } catch (EventNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Invalid event"), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Fetches a list of events the user is registered for or can manage depending on the user type.
     * @param userId user id
     * @return response indicating success/failure along with a list of events in the case of a success
     */
    @GetMapping("")
    public ResponseEntity<Response> getEvents(@RequestParam(value = "userId") String userId) {
        try {
            List<EventData> events = eventManager.getEvents(userId);
            return new ResponseEntity<>(new ResponseArray(true, events), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Invalid user"), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Allows an organizer to create an event based on their inputs.
     * @param creationResource A resource containing the information needed to create an event.
     * @return response indicating sucess/failure
     */
    @PostMapping("")
    public ResponseEntity<Response> createEvent(@RequestBody EventCreationRequest creationResource) {
        try {
            LocalDateTime startTime = LocalDateTime.ofEpochSecond(creationResource.getStartTime(), 0, ZoneOffset.UTC);
            LocalDateTime endTime = LocalDateTime.ofEpochSecond(creationResource.getEndTime(), 0, ZoneOffset.UTC);
            EventData eventData = new EventData(creationResource.getName(), creationResource.getDescription(),
                                                startTime, endTime,
                                                creationResource.getRoomId(), creationResource.getSpeakerLimit(),
                                                creationResource.getUserLimit(), creationResource.isVipOnlyEvent());

            eventManager.createEvent(eventData);
            return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
        } catch (FullRoomException e) {
            return new ResponseEntity<>(new Response(false, "Full room"), HttpStatus.FORBIDDEN);
        } catch (RoomNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Invalid room"), HttpStatus.FORBIDDEN);
        }
    }


}