package group0153.conferencesystem.adapters.gateways.event;

import group0153.conferencesystem.entities.event.Event;

import java.util.Optional;

public class EventMapper {

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
