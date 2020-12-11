package group0153.conferencesystem.adapters.controllers.event;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.adapters.controllers.event.resource.EventUpdateCapacityResource;
import group0153.conferencesystem.application.event.EventRegistry;
import group0153.conferencesystem.application.event.EventScheduleDataPreparer;
import group0153.conferencesystem.application.event.EventScheduler;
import group0153.conferencesystem.application.event.EventUpdater;
import group0153.conferencesystem.application.event.data.EventData;
import group0153.conferencesystem.application.room.RoomManager;
import group0153.conferencesystem.application.user.UserEventsManager;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.UserNotFoundException;
import group0153.conferencesystem.exceptions.eventExceptions.EventNotFoundException;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/event")
public class EventController {
    private final UserEventsManager userEventsManager;
    private final RoomManager roomManager;
    private final EventRegistry eventRegistry;
    private final EventScheduler eventScheduler;
    private final EventUpdater eventUpdater;
    private final EventScheduleDataPreparer eventScheduleDataPreparer;

    public EventController(UserEventsManager userEventsManager, RoomManager roomManager, EventRegistry eventRegistry,
                           EventScheduler eventScheduler, EventUpdater eventUpdater,
                           EventScheduleDataPreparer eventScheduleDataPreparer) {
        this.userEventsManager = userEventsManager;
        this.roomManager = roomManager;
        this.eventRegistry = eventRegistry;
        this.eventScheduler = eventScheduler;
        this.eventUpdater = eventUpdater;
        this.eventScheduleDataPreparer = eventScheduleDataPreparer;
    }

    /**
     * API call to view every single active event. This will be used to give the user the option of registering to
     * possible events. Only the events the user is *not* in are shown.
     *
     * @return A list of every single event and their information.
     */
    @GetMapping("/view")
    public ResponseEntity<Response> getAllEvents(@RequestParam(value = "userId") String userId) {
        try {
            List<String> userEvents = userEventsManager.getUserEvents(userId);
            List<EventData> events = this.eventScheduleDataPreparer.getUpcomingEventsExcluding(userEvents);
            return new ResponseEntity<>(new ResponseArray(true, events), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Invalid user"), HttpStatus.FORBIDDEN);
        }
    }

    /**
     *
     * @param userId The id of the user to be registered.
     * @param eventId The id of the event the user is to be registered to.
     * @return Success response entity if the action was successful. Fail response entity otherwise.
     */
    @GetMapping("/register-event")
    public ResponseEntity<Response> registerUserForEvent(@RequestParam(value = "userId") String userId,
                                                         @RequestParam(value = "eventId") String eventId) {
        try {
            boolean added = eventRegistry.addUserIdToEventUserIdList(eventId, userId, this.userEventsManager.getUserType(userId));
            if (added){
                userEventsManager.addUserEvents(userId, eventId);
                return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response(true, "FAILED, CAPACITY REACHED"), HttpStatus.OK);
            }
        } catch (UnsuccessfulCommandException e) {
            return new ResponseEntity<>(new Response(false, "User could not be registered"), HttpStatus.OK);
        }
    }

    @GetMapping("/unregister-event")
    public ResponseEntity<Response> unregisterEvent(@RequestParam(value = "userId") String userId,
                                                    @RequestParam(value = "eventId") String eventId) {
        try {
            boolean removed = eventRegistry.removeUserIdFromEventUserIdList(eventId, userId);
            if (removed){
                userEventsManager.removeUserEvents(userId, eventId);
                return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Response(true, "User was not registered to this event"), HttpStatus.OK);
            }
        } catch (EventNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Event not found"), HttpStatus.OK);
        }
    }

    /**
     *
     * @param eventName The name of the event.
     * @param description The event's description.
     * @param startTime The start time of the event (this should be an integer representing the time in seconds).
     * @param endTime The end time of the event (this should be an integer representing the time in seconds).
     * @param roomId The id of the room that this event takes place in.
     * @param speakerIds The ids of the speakers for this event.
     * @param userLimit The user limit of this event.
     * @param isVipOnlyEvent Whether this event is for vip's only or not.
     * @return Success response entity if the action was successfull, otherwise fail response entity.
     */
    @GetMapping("/add-Event")
    public ResponseEntity<Response> addEvent(@RequestParam(value = "eventName") String eventName,
                                             @RequestParam(value = "description") String description,
                                             @RequestParam(value = "startTime") long startTime,
                                             @RequestParam(value = "endTime") long endTime,
                                             @RequestParam(value = "roomId") String roomId,
                                             @RequestParam(value = "speakerIds") ArrayList<String> speakerIds,
                                             @RequestParam(value = "userLimit") int userLimit,
                                             @RequestParam(value = "isVipOnlyEvent") boolean isVipOnlyEvent) {
        try {
            this.eventScheduler.scheduleEvent(eventName, description, startTime, endTime, roomId, speakerIds, userLimit, isVipOnlyEvent);
            return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
        } catch (UnsuccessfulCommandException exception) {
            return new ResponseEntity<>(new Response(false, exception.getMessage()), HttpStatus.OK);
        }
    }

    /**
     *
     * @param eventId The id of the event to be cancelled.
     * @return Success response entity if the action was successfull. Fail rewsponse entity otherwise.
     */
    @GetMapping("/cancel-Event")
    public ResponseEntity<Response> cancelEvent(@RequestParam(value = "eventId") String eventId){
        try {
            ArrayList<String> userIds = this.eventScheduler.unScheduleEvent(eventId);
            for (String id : userIds) {
                this.userEventsManager.removeUserEvents(id, eventId);
            }
            return new ResponseEntity<>(new Response(true, "SUCCESS"), HttpStatus.OK);
        } catch (UnsuccessfulCommandException exception) {
            return new ResponseEntity<>(new Response(false, exception.getMessage()), HttpStatus.OK);
        }
    }



    //TODO: change this, event builder does not exist now, and event now has speakerIds.
//    /**
//     * Add event to the list of event user can register.
//     * @param eventType The type of event that is being added.
//     * @param eventName The name of the event.
//     * @param description The description of the event.
//     * @param startTime The start time of the event given in the form year\month\monthDay\hour\minute.
//     * @param endTime The end time of the event given in the form year\month\monthDay\hour\minute.
//     * @param roomId The id of the room that this event takes place in.
//     * @param speakerIds The speaker id list of this event.
//     * @param userLimit The user limit of this event.
//     * @param isVipOnlyEvent If this event is for vip's only.
//     * @return ResponseEntity.
//     */
//
//    @PostMapping("/add")
//    public ResponseEntity<Response> addEvent(@RequestParam(value = "eventType") String eventType,
//                                             @RequestParam(value = "eventName") String eventName,
//                                             @RequestParam(value = "description") String description,
//                                             @RequestParam(value = "startTime") String startTime,
//                                             @RequestParam(value = "endTime") String endTime,
//                                             @RequestParam(value = "roomId") String roomId,
//                                             @RequestParam(value = "speakerIds") ArrayList<String> speakerIds,
//                                             @RequestParam(value = "userLimit") int userLimit,
//                                             @RequestParam(value = "isVipOnlyEvent") boolean isVipOnlyEvent) {
//        try {
//            this.setRequiredEventAttributes(eventName, description, startTime, endTime, roomId, userLimit, isVipOnlyEvent);
//            EventData eventData = this.eventScheduler.scheduleEvent(this.eventBuilder.build(eventType));
//            return new ResponseEntity<>(new Re)
//        } catch (UnsuccessfulCommandException exception) {
//
//        }
//    }
//
//    /**
//     * Sets the attributes of the event class that are present in all subclasses as well.
//     *
//     * @param id             The id of the event.
//     * @param eventName      The event name.
//     * @param description    The event description.
//     * @param startTime      The start time of the event.
//     * @param endTime        The end time of the event.
//     * @param roomId         The room id of the event.
//     * @param userLimit      The user limit of the event.
//     * @param isVipOnlyEvent If the event is for vip's only.
//     */
//    private void setRequiredEventAttributes(String eventName, String description, String startTime, String endTime,
//                                            String roomId, int userLimit, boolean isVipOnlyEvent) throws UnsuccessfulCommandException {
//        this.eventBuilder.setEventName(eventName);
//        this.eventBuilder.setDescription(description);
//        this.eventBuilder.setStartTime(startTime);
//        this.eventBuilder.setEndTime(endTime);
//        this.eventBuilder.setRoomId(roomId);
//        this.eventBuilder.setUserLimit(userLimit);
//        this.eventBuilder.setIsVipOnlyEvent(isVipOnlyEvent);
//    }
//
    @PostMapping("/update-capacity")
    public ResponseEntity<Response> updateCapacity(@RequestBody EventUpdateCapacityResource capacityResource) {
        try {
            eventUpdater.updateCapacity(capacityResource.getEventId(), capacityResource.getUserLimit());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (EventNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "Event not found"), HttpStatus.OK);
        }
    }


//    /**
//     *
//     * @param eventType A string representing this event's type (not case sensitive).
//     *                  Valid types here are:
//     *                  NoSpeakerEvent
//     * @param id The id of this event.
//     * @param eventName The name of this event.
//     * @param description The description of this event.
//     * @param startTime The start time of this event.
//     * @param endTime The end time of this event.
//     * @param roomId The id of the room this event is taking place in.
//     * @param userLimit The user limit of this event.
//     * @param isVipOnlyEvent Whether this is a vip only event or not.
//     */
//    public void addEvent(String eventType, String id, String eventName, String description, Date startTime, Date endTime,
//                           String roomId, int userLimit, boolean isVipOnlyEvent) throws CommandException {
//        Event event;
//        setRequiredEventAttributes(id, eventName, description, startTime, endTime, roomId, userLimit, isVipOnlyEvent);
//        if (eventType.equalsIgnoreCase("NoSpeakerEvent")) {
//            event = this.eventBuilder.build("NoSpeakerEvent");
//            this.eventScheduler.scheduleEvent(event);
//        }
//        throw new CommandException(eventType + " is not a valid event type matching the given parameters.");
//    }
//
//    /**
//     *
//     * @param eventType A string representing this event's type (not case sensitive).
//     *                  Valid types here are:
//     *                  MultiSpeakerEvent
//     * @param id The id of this event.
//     * @param eventName The name of this event.
//     * @param description The description of this event.
//     * @param startTime The start time of this event.
//     * @param endTime The end time of this event.
//     * @param roomId The id of the room this event is taking place in.
//     * @param userLimit The user limit of this event.
//     * @param isVipOnlyEvent Whether this is a vip only event or not.
//     * @param speakerIds A list of the speakerIds associated with this event.
//     */
//    public void addEvent(String eventType, String id, String eventName, String description, Date startTime, Date endTime,
//                           String roomId, int userLimit, boolean isVipOnlyEvent, ArrayList<String> speakerIds) throws CommandException {
//        Event event;
//        setRequiredEventAttributes(id, eventName, description, startTime, endTime, roomId, userLimit, isVipOnlyEvent);
//        if (eventType.equalsIgnoreCase("MultiSpeakerEvent")) {
//            this.eventBuilder.setSpeakerIds(speakerIds);
//            event = this.eventBuilder.build("MultiSpeakerEvent");
//            this.eventScheduler.scheduleEvent(event);
//        }
//
//        throw new CommandException(eventType + " is not a valid event type matching the given parameters");
//    }
//
//    /**
//     *
//     * @param eventType A string representing this event's type (not case sensitive).
//     *                  Valid types here are:
//     *                  OneSpeakerEvent
//     * @param id The id of this event.
//     * @param eventName The name of this event.
//     * @param description The description of this event.
//     * @param startTime The start time of this event.
//     * @param endTime The end time of this event.
//     * @param roomId The id of the room this event is taking place in.
//     * @param userLimit The user limit of this event.
//     * @param isVipOnlyEvent Whether this is a vip only event or not.
//     * @param speakerId The speaker of this event.
//     */
//    public void addEvent(String eventType, String id, String eventName, String description, Date startTime, Date endTime,
//                           String roomId, int userLimit, boolean isVipOnlyEvent, String speakerId) throws CommandException {
//        Event event;
//        setRequiredEventAttributes(id, eventName, description, startTime, endTime, roomId, userLimit, isVipOnlyEvent);
//        if (eventType.equalsIgnoreCase("OneSpeakerEvent")) {
//            this.eventBuilder.setSpeakerId(speakerId);
//            event = this.eventBuilder.build("OneSpeakerEvent");
//            this.eventScheduler.scheduleEvent(event);
//        }
//        throw new CommandException(eventType + " is not a valid event type matching the given parameters");
//    }
//
//    public void registerUserForEvent(String userId, String eventId) throws CommandException {
//        this.eventRegistry.registerUserForEvent(userId, eventId);
//    }
//
//    public void registerSpeakerForEvent(String speakerId, String eventId) throws CommandException {
//        this.eventRegistry.registerSpeakerForEvent(speakerId, eventId);
//    }
//
//    /**
//     * Sets the required attributes of the event. These attributes are all of the attributes in the uppermost Event superclass
//     * which are common among all of the subclasses of Event.
//     * @param id The id of this event.
//     * @param eventName The name of this event.
//     * @param description The description of this event.
//     * @param startTime The start time of this event.
//     * @param endTime The end time of this event.
//     * @param roomId The id of the room this event is taking place in.
//     * @param userLimit The user limit of this event.
//     * @param isVipOnlyEvent Whether this is a vip only event or not.
//     */

//    //TODO: more event controller methods here

}