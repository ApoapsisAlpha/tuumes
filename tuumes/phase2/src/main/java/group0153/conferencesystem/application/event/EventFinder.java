package group0153.conferencesystem.application.event;

import group0153.conferencesystem.application.event.data.EventData;
import group0153.conferencesystem.application.exceptions.UserNotFoundException;
import group0153.conferencesystem.application.room.RoomPersistencePort;
import group0153.conferencesystem.application.user.UserPersistencePort;
import group0153.conferencesystem.application.user.data.UserContactData;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.entities.user.User;
import group0153.conferencesystem.entities.user.UserType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A manager class get the list of events according by request
 */
@Component
public class EventFinder {
    final UserPersistencePort userPersistencePort;
    final EventPersistencePort eventPersistencePort;
    final RoomPersistencePort roomPersistencePort;

    /**
     * Constructs a new instance of EventManager using the provided ports to provide access to data
     *
     * @param userPersistencePort  instance of UserPersistencePort, giving access to User data
     * @param eventPersistencePort instance of EventPersistencePort, giving access to Event data
     * @param roomPersistencePort  instance of RoomPersistencePort, giving access to Room data
     */
    public EventFinder(UserPersistencePort userPersistencePort, EventPersistencePort eventPersistencePort,
                       RoomPersistencePort roomPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.eventPersistencePort = eventPersistencePort;
        this.roomPersistencePort = roomPersistencePort;
    }

    /**
     * Get a list of available events for the given user.
     *
     * @param userId user id
     * @return list of events which are available
     * @throws UserNotFoundException no user corresponds to the provided id
     */
    public List<EventData> getAvailableEvents(String userId) throws UserNotFoundException {
        User user = userPersistencePort.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Event> events = eventPersistencePort.getAllEvents();
        LocalDateTime currentTime = LocalDateTime.now();
        return events.stream().filter(e -> user.getType() == UserType.VIP || !e.isVipOnlyEvent())
                .filter(e -> e.getStartTime().isAfter(currentTime))
                .filter(e -> !user.getEvents().contains(e.getId()))
                .filter(e -> user.getType() != UserType.ATTENDEE || !hasEventCollision(user, e))
                .map(this::mapEntityToData)
                .collect(Collectors.toList());
    }

    /**
     * Get a list of events registered by the given user.
     *
     * @param userId the user id
     * @return a list of events
     * @throws UserNotFoundException no user corresponds to the provided id.
     */
    public List<EventData> getEvents(String userId) throws UserNotFoundException {
        User user = userPersistencePort.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<Event> events;
        if (user.getType() == UserType.ORGANIZER) {
            events = eventPersistencePort.getAllEvents();
        } else {
            events = user.getEvents().stream().flatMap(eventId -> {
                Optional<Event> event = eventPersistencePort.findById(eventId);
                return event.map(Stream::of).orElseGet(Stream::empty);
            }).collect(Collectors.toList());
        }

        return events.stream().map(this::mapEntityToData).collect(Collectors.toList());
    }

    // private methods

    /**
     * Maps the given entity to a data object
     * @param e the event
     * @return and EventData object
     */
    private EventData mapEntityToData(Event e) {
        List<UserContactData> speakerData = e.getSpeakerIds().stream().map(speakerId -> {
            User speaker = userPersistencePort.findById(speakerId).get();
            return new UserContactData(speakerId, speaker.getName(), speaker.getEmail());
        }).collect(Collectors.toList());

        EventData data = new EventData(e);
        data.setSpeakerData(speakerData);
        data.setRoomName(roomPersistencePort.findById(e.getRoomId()).get().getName());
        return data;
    }

    /**
     * Checks if there is a collision with the given user and event. Specifically, it checks whether the user
     * has an event at the same time as the provided event.
     *
     * @param user  the user
     * @param event the event
     * @return true if there is a collision, false otherwise
     */
    private boolean hasEventCollision(User user, Event event) {
        // Gets a list of the user's events
        List<Event> events = user.getEvents().stream().flatMap(eventId -> eventPersistencePort.findById(eventId).map(Stream::of).orElseGet(Stream::empty)).collect(Collectors.toList());

        return events.stream().anyMatch(userEvent -> areEventsOverlapping(userEvent, event));
    }

    /**
     * Checks if the two events are overlapping.
     * @param event1 first event
     * @param event2 second event
     * @return true if they are overlapping, false otherwise
     */
    private boolean areEventsOverlapping(Event event1, Event event2) {
        return event1.getEndTime().isAfter(event2.getStartTime()) &&
                event1.getStartTime().isBefore(event2.getEndTime());
    }

}
