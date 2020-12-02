package group0153.conferencesystem.adapters.controllers;

import group0153.conferencesystem.application.EventFactory;
import group0153.conferencesystem.application.EventManager;
import group0153.conferencesystem.entities.Event;
import group0153.conferencesystem.entities.MultiSpeakerEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private EventManager eventManager;
    private EventFactory eventFactory;

    public EventController(EventManager eventManager) {
        this.eventManager = eventManager;
        this.eventFactory = new EventFactory();
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
