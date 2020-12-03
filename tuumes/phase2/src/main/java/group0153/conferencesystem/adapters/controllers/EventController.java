package group0153.conferencesystem.adapters.controllers;

import group0153.conferencesystem.application.EventBuilder;
import group0153.conferencesystem.application.EventManager;
import group0153.conferencesystem.entities.Event;
import group0153.conferencesystem.entities.MultiSpeakerEvent;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class EventController {

    private EventManager eventManager;
    private EventBuilder eventBuilder;

    public EventController(EventManager eventManager) {
        this.eventManager = eventManager;
        this.eventBuilder = new EventBuilder();
    }

//    @PostMapping("/events")
//    String createEvent(@RequestBody EventResource event) {
//        return eventManager.createEvent(
//                event.getEventName(),
//                event.getDescription(),
//                event.getStartTime(),
//                event.getEndTime(),
//                event.getRoom(),
//                event.getSpeakerIds(),
//                event.getUserLimit(),
//                event.isVipOnlyEvent());
//    }

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
     * @return Returns "Time conflict." or "Invalid parameters." if event is not added. Returns "Success." otherwise.
     */
    public String addEvent(String eventType, String id, String eventName, String description, Date startTime, Date endTime,
                           String roomId, int userLimit, boolean isVipOnlyEvent) {
        Event event;
        if (eventType.equalsIgnoreCase("NoSpeakerEvent")) {
            this.eventBuilder.setEventAttributes(id, eventName, description, startTime, endTime,
                    roomId, userLimit, isVipOnlyEvent);
            event = this.eventBuilder.build("NoSpeakerEvent");
        }
        return "Invalid parameters.";
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
     * @return Returns "Time conflict." or "Invalid parameters." if event is not added. Returns "Success." otherwise.
     */
    public String addEvent(String eventType, String id, String eventName, String description, Date startTime, Date endTime,
                           String roomId, int userLimit, boolean isVipOnlyEvent, ArrayList<String> speakerIds) {
        Event event;
        if (eventType.equalsIgnoreCase("MultiSpeakerEvent")) {
            this.eventBuilder.setEventAttributes(id, eventName, description, startTime, endTime,
                    roomId, userLimit, isVipOnlyEvent, speakerIds);
            event = this.eventBuilder.build("MultiSpeakerEvent");
        }
        return "Invalid parameters.";
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
     * @return Returns "Time conflict." or "Invalid parameters." if event is not added. Returns "Success." otherwise.
     */
    public String addEvent(String eventType, String id, String eventName, String description, Date startTime, Date endTime,
                           String roomId, int userLimit, boolean isVipOnlyEvent, String speakerId) {
        Event event;
        if (eventType.equalsIgnoreCase("OneSpeakerEvent")) {
            this.eventBuilder.setEventAttributes(id, eventName, description, startTime, endTime,
                    roomId, userLimit, isVipOnlyEvent, speakerId);
            event = this.eventBuilder.build("OneSpeakerEvent");
        }
        return "Invalid parameters.";
    }
    //TODO: more event controller methods here

}
