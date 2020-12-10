package group0153.conferencesystem.adapters.controllers.event;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.EventData;
import group0153.conferencesystem.application.event.EventScheduleDataPreparer;
import group0153.conferencesystem.application.room.RoomManager;
import group0153.conferencesystem.application.room.data.RoomData;
import group0153.conferencesystem.application.user.UserEventsManager;
import group0153.conferencesystem.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/event")
public class EventController {
    private final UserEventsManager userEventsManager;
    private final RoomManager roomManager;

    public EventController(UserEventsManager userEventsManager, RoomManager roomManager) {
        this.userEventsManager = userEventsManager;
        this.roomManager = roomManager;
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
            // TODO: 12/9/2020 @weihao This event should return every event a user is not a part of. (only future events too)
            // This should return List<EventData>
            List<Data> events = WhichEvent.getEventsForUser(userEvents);
            for (Data event : events) {
                RoomData room = roomManager.getRoomById(event.)
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(false, "BAD_USER"), HttpStatus.FORBIDDEN);
        }
    }

//    private final Event.Builder eventBuilder;
//    private final EventRegistry eventRegistry;
//    private final EventScheduler eventScheduler;
//    private final EventUpdater eventUpdater
//
//    public EventController(EventManager eventManager) {
//        ArrayList<Event> events = new ArrayList<Event>();
//        this.eventRegistry = new EventRegistry(events);
//        this.eventScheduler = new EventScheduler(events);
//        this.eventUpdater = new EventUpdater(events);
//        this.eventBuilder = new Event.Builder();
//    }
//
//    /**
//     *
//     * @param eventManager
//     * @param events A list of the
//     */
//    public EventController( EventPersistencePort eventPersistencePort) {
//        this.eventRegistry = new EventRegistry(eventPersistencePort);
//        this.eventScheduler = new EventScheduler(eventPersistencePort);
//        this.eventUpdater = new EventUpdater(eventPersistencePort);
//
//        this.eventBuilder = new Event.Builder();
//    }
//
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
//    private void setRequiredEventAttributes(String id, String eventName, String description, Date startTime, Date endTime,
//                                         String roomId, int userLimit, boolean isVipOnlyEvent) {
//        this.eventBuilder.setId(id);
//        this.eventBuilder.setEventName(eventName);
//        this.eventBuilder.setDescription(description);
//        this.eventBuilder.setStartTime(startTime);
//        this.eventBuilder.setEndTime(endTime);
//        this.eventBuilder.setRoomId(roomId);
//        this.eventBuilder.setUserLimit(userLimit);
//        this.eventBuilder.setIsVipOnlyEvent(isVipOnlyEvent);
//    }
//    //TODO: more event controller methods here
//    public void updateEventCapacity(String eventId, int newCapacity){
//        eventUpdater.updateCapacity(eventId, newCapacity);
//    }
}
