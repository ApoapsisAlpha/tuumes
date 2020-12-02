package group0153.conferencesystem.adapters.controllers;

import group0153.conferencesystem.application.EventManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private EventManager eventManager;

    public EventController(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @PostMapping("/events")
    String createEvent(@RequestBody EventResource event) {
        return eventManager.createEvent(
                event.getEventName(),
                event.getDescription(),
                event.getStartTime(),
                event.getEndTime(),
                event.getRoom(),
                event.getSpeakerIds(),
                event.getUserLimit(),
                event.isVipOnlyEvent());
    }

    //TODO: more event controller methods here
}
