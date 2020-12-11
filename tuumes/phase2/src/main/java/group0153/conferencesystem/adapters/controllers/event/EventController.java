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

import java.util.List;

@RestController
@RequestMapping(value="/events")
public class EventController {

    private EventManager eventManager;

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

    @PostMapping("")
    public ResponseEntity<Response> createEvent(@RequestBody EventCreationRequest creationResource) {
        try {
            EventData eventData = new EventData(creationResource.getName(), creationResource.getDescription(),
                                                creationResource.getStartTime(), creationResource.getEndTime(),
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