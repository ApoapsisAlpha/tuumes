package group0153.conferencesystem.application.event;

import group0153.conferencesystem.application.event.data.EventData;
import group0153.conferencesystem.application.exceptions.*;
import group0153.conferencesystem.application.room.RoomPersistencePort;
import group0153.conferencesystem.application.user.UserPersistencePort;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.entities.room.Room;
import group0153.conferencesystem.entities.user.User;
import group0153.conferencesystem.entities.user.UserType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A manager class that manipulates events
 */
@Component
public class EventManager {

    UserPersistencePort userPersistencePort;
    EventPersistencePort eventPersistencePort;
    RoomPersistencePort roomPersistencePort;

    /**
     * Constructs a new instance of EventManager using the provided ports to provide access to data
     *
     * @param userPersistencePort  instance of UserPersistencePort, giving access to User data
     * @param eventPersistencePort instance of EventPersistencePort, giving access to Event data
     * @param roomPersistencePort  instance of RoomPersistencePort, giving access to Room data
     */
    public EventManager(UserPersistencePort userPersistencePort, EventPersistencePort eventPersistencePort,
                        RoomPersistencePort roomPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.eventPersistencePort = eventPersistencePort;
        this.roomPersistencePort = roomPersistencePort;
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
        List<Event> events = user.getEvents().stream().flatMap(eventId -> {
            return eventPersistencePort.findById(eventId).map(Stream::of).orElseGet(Stream::empty);
        }).collect(Collectors.toList());

        return events.stream().anyMatch(userEvent -> {
            return userEvent.getEndTime().isAfter(event.getStartTime()) &&
                    userEvent.getStartTime().isBefore(event.getEndTime());
        });
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
                .map(EventData::new)
                .collect(Collectors.toList());
    }

    /**
     * Get a list of events for the given user.
     *
     * @param userId the user id
     * @return a list of events
     */
    public List<EventData> getEvents(String userId) {
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

        return events.stream().map(EventData::new).collect(Collectors.toList());
    }

    /**
     * Register the given user for the provided event.
     *
     * @param eventId event id
     * @param userId  user id
     * @throws UserNotFoundException no user corresponds with the provided id
     * @throws EventNotFoundException no event corresponds with the provided id
     * @throws FullEventException event specified is full
     * @throws VipOnlyEventException event specified is only open for registration by VIPs
     */
    public void registerUserForEvent(String eventId, String userId) throws UserNotFoundException,
            EventNotFoundException, FullEventException {
        User user = userPersistencePort.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Event event = eventPersistencePort.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));

        if (event.getUserCount() >= event.getUserLimit())
            throw new FullEventException(eventId);
        if (event.isVipOnlyEvent() && !user.getType().equals(UserType.VIP)) throw new VipOnlyEventException(eventId);
        eventPersistencePort.registerUserById(eventId, userId);
        if (user.getType() == UserType.SPEAKER) {
            if (event.getSpeakerCount() >= event.getSpeakerLimit())
                throw new FullEventException(eventId);
            if (!hasEventCollision(user, event))
                eventPersistencePort.registerSpeakerById(eventId, userId);
        }
    }

    /**
     * Create an event with the given event data.
     *
     * @param eventData event data
     * @throws RoomNotFoundException if room is not found
     * @throws FullRoomException     if the room is full
     */
    public void createEvent(EventData eventData) throws RoomNotFoundException, FullRoomException {
        Event event = new Event(UUID.randomUUID().toString(), eventData.getName(), eventData.getDescription(),
                eventData.getStartTime(), eventData.getEndTime(), eventData.getRoomId(),
                eventData.getSpeakerLimit(), eventData.getUserLimit(), eventData.isVipOnlyEvent());

        Room room = roomPersistencePort.findById(event.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException(event.getRoomId()));

        List<Event> roomEvents = eventPersistencePort.getAllEvents().stream()
                .filter(e -> e.getRoomId().equals(room.getId()))
                .collect(Collectors.toList());
        // sum of user limits for all events in the room
        int totalRoomEventLimit = roomEvents.stream().mapToInt(Event::getUserLimit).sum();
        if (totalRoomEventLimit + event.getUserLimit() >= room.getCapacity())
            throw new FullRoomException(room.getId());

        eventPersistencePort.saveEvent(event);
    }


}
