package group0153.conferencesystem.adapters.controllers.event;

import group0153.conferencesystem.application.event.EventBuilder;
import group0153.conferencesystem.application.event.EventManager;
import group0153.conferencesystem.application.event.EventRegistry;
import group0153.conferencesystem.application.event.EventScheduler;
import group0153.conferencesystem.application.event.exception.EventNotFoundException;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.CommandException;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class EventController {

    private final EventManager eventManager;
    private final EventBuilder eventBuilder;
    private final EventRegistry eventRegistry;
    private final EventScheduler eventScheduler;

    public EventController(EventManager eventManager) {
        ArrayList<Event> events = new ArrayList<Event>();
        this.eventRegistry = new EventRegistry(events);
        this.eventScheduler = new EventScheduler(events);
        this.eventManager = eventManager;
        this.eventBuilder = new EventBuilder();
    }

    /**
     *
     * @param eventManager
     * @param events A list of the
     */
    public EventController(EventManager eventManager, ArrayList<Event> events) {
        this.eventRegistry = new EventRegistry(events);
        this.eventScheduler = new EventScheduler(events);
        this.eventManager = eventManager;
        this.eventBuilder = new EventBuilder();
    }

    /**
     *
     * @param eventType A string representing this event's type (not case sensitive).
     *                  Valid types here are:
     *                  NoSpeakerEvent
     * @param id The id of this event.
     * @param eventName The name of this event.
     * @param description The description of this event.
     * @param startTime The start time of this event.
     * @param endTime The end time of this event.
     * @param roomId The id of the room this event is taking place in.
     * @param userLimit The user limit of this event.
     * @param isVipOnlyEvent Whether this is a vip only event or not.
     */
    public void addEvent(String eventType, String id, String eventName, String description, Date startTime, Date endTime,
                           String roomId, int userLimit, boolean isVipOnlyEvent) throws CommandException {
        Event event;
        setRequiredEventAttributes(id, eventName, description, startTime, endTime, roomId, userLimit, isVipOnlyEvent);
        if (eventType.equalsIgnoreCase("NoSpeakerEvent")) {
            event = this.eventBuilder.build("NoSpeakerEvent");
            this.eventScheduler.scheduleEvent(event);
        }
        throw new CommandException(eventType + " is not a valid event type matching the given parameters.");
    }

    /**
     *
     * @param eventType A string representing this event's type (not case sensitive).
     *                  Valid types here are:
     *                  MultiSpeakerEvent
     * @param id The id of this event.
     * @param eventName The name of this event.
     * @param description The description of this event.
     * @param startTime The start time of this event.
     * @param endTime The end time of this event.
     * @param roomId The id of the room this event is taking place in.
     * @param userLimit The user limit of this event.
     * @param isVipOnlyEvent Whether this is a vip only event or not.
     * @param speakerIds A list of the speakerIds associated with this event.
     */
    public void addEvent(String eventType, String id, String eventName, String description, Date startTime, Date endTime,
                           String roomId, int userLimit, boolean isVipOnlyEvent, ArrayList<String> speakerIds) throws CommandException {
        Event event;
        setRequiredEventAttributes(id, eventName, description, startTime, endTime, roomId, userLimit, isVipOnlyEvent);
        if (eventType.equalsIgnoreCase("MultiSpeakerEvent")) {
            this.eventBuilder.setSpeakerIds(speakerIds);
            event = this.eventBuilder.build("MultiSpeakerEvent");
            this.eventScheduler.scheduleEvent(event);
        }

        throw new CommandException(eventType + " is not a valid event type matching the given parameters");
    }

    /**
     *
     * @param eventType A string representing this event's type (not case sensitive).
     *                  Valid types here are:
     *                  OneSpeakerEvent
     * @param id The id of this event.
     * @param eventName The name of this event.
     * @param description The description of this event.
     * @param startTime The start time of this event.
     * @param endTime The end time of this event.
     * @param roomId The id of the room this event is taking place in.
     * @param userLimit The user limit of this event.
     * @param isVipOnlyEvent Whether this is a vip only event or not.
     * @param speakerId The speaker of this event.
     */
    public void addEvent(String eventType, String id, String eventName, String description, Date startTime, Date endTime,
                           String roomId, int userLimit, boolean isVipOnlyEvent, String speakerId) throws CommandException {
        Event event;
        setRequiredEventAttributes(id, eventName, description, startTime, endTime, roomId, userLimit, isVipOnlyEvent);
        if (eventType.equalsIgnoreCase("OneSpeakerEvent")) {
            this.eventBuilder.setSpeakerId(speakerId);
            event = this.eventBuilder.build("OneSpeakerEvent");
            this.eventScheduler.scheduleEvent(event);
        }
        throw new CommandException(eventType + " is not a valid event type matching the given parameters");
    }

    public void registerUserForEvent(String userId, String eventId) throws EventNotFoundException {
        this.eventRegistry.addUserIdToEventUserIdList(userId, eventId);
    }

    public void registerSpeakerForEvent(String speakerId, String eventId) throws CommandException {
        this.eventRegistry.registerSpeakerForEvent(speakerId, eventId);
    }

    /**
     * Sets the required attributes of the event. These attributes are all of the attributes in the uppermost Event superclass
     * which are common among all of the subclasses of Event.
     * @param id The id of this event.
     * @param eventName The name of this event.
     * @param description The description of this event.
     * @param startTime The start time of this event.
     * @param endTime The end time of this event.
     * @param roomId The id of the room this event is taking place in.
     * @param userLimit The user limit of this event.
     * @param isVipOnlyEvent Whether this is a vip only event or not.
     */
    private void setRequiredEventAttributes(String id, String eventName, String description, Date startTime, Date endTime,
                                         String roomId, int userLimit, boolean isVipOnlyEvent) {
        this.eventBuilder.setId(id);
        this.eventBuilder.setEventName(eventName);
        this.eventBuilder.setDescription(description);
        this.eventBuilder.setStartTime(startTime);
        this.eventBuilder.setEndTime(endTime);
        this.eventBuilder.setRoomId(roomId);
        this.eventBuilder.setUserLimit(userLimit);
        this.eventBuilder.setIsVipOnlyEvent(isVipOnlyEvent);
    }
    //TODO: more event controller methods here

}
