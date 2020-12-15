package group0153.conferencesystem.adapters.controllers.event;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.adapters.controllers.event.requests.EventCreationRequest;
import group0153.conferencesystem.adapters.controllers.event.requests.EventRegistrationRequest;
import group0153.conferencesystem.adapters.controllers.event.requests.EventRemoveRequest;
import group0153.conferencesystem.adapters.controllers.event.requests.EventUpdateCapacityRequest;
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
@RequestMapping(value="/api/events")
public class EventController {

    private final EventRegistrationManager eventRegistrationManager;
    private final EventFinder eventFinder;
    private final EventUpdateManager eventUpdateManager;

    /**
     * Creates an EventController instance.
     * @param eventRegistrationManager an instance of EventRegistryManager
     * @param eventFinder an instance of EventGetter
     */
    public EventController(EventRegistrationManager eventRegistrationManager, EventFinder eventFinder,
                           EventUpdateManager eventUpdateManager) {
        this.eventRegistrationManager = eventRegistrationManager;
        this.eventFinder = eventFinder;
        this.eventUpdateManager = eventUpdateManager;
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
            List<EventData> events = eventFinder.getAvailableEvents(userId);
            return new ResponseEntity<>(new ResponseArray(true, events), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Registers a user to an event.
     * @param request request data
     * @return response indicating success/failure
     */
    @PostMapping("/register")
    public ResponseEntity<Response> registerUserForEvent(@RequestBody EventRegistrationRequest request) {
        try {
            eventRegistrationManager.registerUserForEvent(request.getEventId(), request.getUserId());
            return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        } catch (EventNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_EVENT"), HttpStatus.FORBIDDEN);
        } catch (FullEventException e) {
            return new ResponseEntity<>(new Response(false, "FULL_EVENT"), HttpStatus.OK);
        } catch (VipOnlyEventException e) {
            return new ResponseEntity<>(new Response(false, "VIP_ONLY"), HttpStatus.OK);
        } catch (SpeakerConflictException e) {
            return new ResponseEntity<>(new Response(false, "SPEAKER_CONFLICT"), HttpStatus.OK);
        }
    }

    /**
     * Unregisters a user from an event.
     * @param request request data
     * @return response indicating success/failure
     */
    @PostMapping("/unregister")
    public ResponseEntity<Response> unregisterUserFromEvent(@RequestBody EventRegistrationRequest request) {
        try {
            eventRegistrationManager.unregisterUserFromEvent(request.getEventId(), request.getUserId());
            return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        } catch (EventNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_EVENT"), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Fetches a list of events the user is registered for or can manage depending on the user type.
     *
     * @param userId user id
     * @return response indicating success/failure along with a list of events in the case of a success
     */
    @GetMapping("")
    public ResponseEntity<Response> getEvents(@RequestParam(value = "userId") String userId) {
        try {
            List<EventData> events = eventFinder.getEvents(userId);
            return new ResponseEntity<>(new ResponseArray(true, events), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Allows an organizer to create an event based on their inputs.
     *
     * @param request A resource containing the information needed to create an event.
     * @return response indicating success/failure
     */
    @PostMapping("")
    public ResponseEntity<Response> createEvent(@RequestBody EventCreationRequest request) {
        try {
            LocalDateTime startTime = LocalDateTime.ofEpochSecond(request.getStartTime(), 0, ZoneOffset.UTC);
            LocalDateTime endTime = LocalDateTime.ofEpochSecond(request.getEndTime(), 0, ZoneOffset.UTC);
            if (startTime.isBefore(endTime)) {
                EventData eventData = new EventData(request.getName(), request.getDescription(),
                        startTime, endTime,
                        request.getRoomId(), request.getSpeakerLimit(),
                        request.getUserLimit(), request.isVipOnlyEvent());

                eventRegistrationManager.createEvent(eventData);
                return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response(false, "TIME_ERROR"), HttpStatus.OK);
            }
        } catch (FullRoomException e) {
            return new ResponseEntity<>(new Response(false, "ROOM_FULL"), HttpStatus.OK);
        } catch (RoomNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "INVALID_ROOM"), HttpStatus.OK);
        } catch (ExistingOverlappingEventException e) {
            return new ResponseEntity<>(new Response(false, "TIME_CONFLICT"), HttpStatus.OK);
        }
    }

    /**
     * Allows an organizer to delete an event based on their inputs.
     *
     * @param request the removal request
     * @return response indicating success/failure
     */
    @PostMapping("/remove")
    public ResponseEntity<Response> deleteEvent(@RequestBody EventRemoveRequest request) {
        try {
            eventRegistrationManager.cancelEvent(request.getEventId());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (EventNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_EVENT"), HttpStatus.OK);
        }
    }

    /**
     * Allows an organizer to update the capacity of an event based on their inputs.
     *
     * @param request The update request
     * @return response indicating success/failure
     */
    @PostMapping("/update_capacity")
    public ResponseEntity<Response> updateEventCapacity(@RequestBody EventUpdateCapacityRequest request) {
        try {
            eventUpdateManager.updateCapacity(request.getEventId(), request.getUserLimit());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (FullRoomException e) {
            return new ResponseEntity<>(new Response(false, "ROOM_FULL"), HttpStatus.OK);
        } catch (EventNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_EVENT"), HttpStatus.OK);
        }
    }

}