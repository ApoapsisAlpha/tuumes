package group0153.conferencesystem.adapters.gateways.event;

import group0153.conferencesystem.entities.event.Event;

import java.util.Optional;

/**
 * A class that converts(maps) between models and entities
 */
public class EventMapper {

    /**
     * Maps between an event model and event entity.
     *
     * @param eventModel A database entity for event
     * @return An event entity with event model's information
     */
    public Optional<Event> mapModelToEntity(Optional<EventModel> eventModel) {
        return eventModel.flatMap(e -> {
            Event event = new Event(e.getResourceId(), e.getName(), e.getDescription(), e.getStartTime(),
                    e.getEndTime(), e.getRoom().getResourceId(), e.getSpeakerLimit(), e.getUserLimit(),
                    e.isVipOnlyEvent());

            e.getSpeakers().forEach(speaker -> event.addSpeakerId(speaker.getResourceId()));
            e.getUsers().forEach(user -> event.addUserId(user.getResourceId()));
            return Optional.of(event);
        });
    }
}
