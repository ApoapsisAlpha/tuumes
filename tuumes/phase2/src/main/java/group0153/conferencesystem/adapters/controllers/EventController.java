package group0153.conferencesystem.adapters.controllers;

import group0153.conferencesystem.application.EventBuilder;
import group0153.conferencesystem.application.EventManager;
import group0153.conferencesystem.entities.Event;
import org.springframework.web.bind.annotation.RestController;

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

    public String addEvent(String eventType) {
        Event event;
        if (eventType.equalsIgnoreCase(MultiSpeakerEvent))
    }
    //TODO: more event controller methods here
}
