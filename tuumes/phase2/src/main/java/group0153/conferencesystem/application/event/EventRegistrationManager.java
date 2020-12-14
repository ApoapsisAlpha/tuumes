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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A manager for event registry, event creation, event cancellation.
 */
@Component
public class EventRegistrationManager {

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
    public EventRegistrationManager(UserPersistencePort userPersistencePort, EventPersistencePort eventPersistencePort,
                                    RoomPersistencePort roomPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.eventPersistencePort = eventPersistencePort;
        this.roomPersistencePort = roomPersistencePort;
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

        if (event.isVipOnlyEvent() && !user.getType().equals(UserType.VIP))
            throw new VipOnlyEventException(eventId);

        eventPersistencePort.registerUserById(eventId, userId);

        if (user.getType() == UserType.SPEAKER) {
            if (event.getSpeakerCount() >= event.getSpeakerLimit())
                throw new FullEventException(eventId);
            if (!hasEventCollision(user, event))
                eventPersistencePort.registerSpeakerById(eventId, userId);
        }
    }

    /**
     * Unregister the given user for the provided event.
     *
     * @param eventId event id
     * @param userId  user id
     * @throws UserNotFoundException no user corresponds with the provided id
     * @throws EventNotFoundException no event corresponds with the provided id
     */
    public void unregisterUserFromEvent(String eventId, String userId) throws UserNotFoundException,
            EventNotFoundException{
        User user = userPersistencePort.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Event event = eventPersistencePort.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));

        eventPersistencePort.unregisterUserById(eventId, userId);
        if (user.getType() == UserType.SPEAKER) {
            eventPersistencePort.unregisterSpeakerById(eventId, userId);
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

        if (roomEvents.stream().anyMatch(roomEvent -> areEventsOverlapping(roomEvent, event)))
            throw new ExistingOverlappingEventException();

        if (event.getUserLimit() > room.getCapacity())
            throw new FullRoomException(room.getId());

        eventPersistencePort.saveEvent(event);
    }

    /**
     * Cancels an event - if it exists - on request
     *
     * @param eventId the String id of the event to be cancelled
     * @throws EventNotFoundException no event corresponds with the provided id
     */
    public void cancelEvent(String eventId) throws EventNotFoundException{
        Event event = eventPersistencePort.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));
        if (!eventPersistencePort.getAllEvents().contains(event)){
            throw new EventNotFoundException(eventId);
        }
        for (String userid : event.getUserIds()){
            unregisterUserFromEvent(eventId, userid);
        }
        eventPersistencePort.deleteEvent(eventId);
    }


    // private methods

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
            return areEventsOverlapping(userEvent, event);
        });
    }
}
