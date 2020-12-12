package group0153.conferencesystem.adapters.gateways.event;

import group0153.conferencesystem.adapters.gateways.room.RoomModel;
import group0153.conferencesystem.adapters.gateways.room.RoomRepository;
import group0153.conferencesystem.adapters.gateways.user.UserModel;
import group0153.conferencesystem.adapters.gateways.user.UserRepository;
import group0153.conferencesystem.application.event.EventPersistencePort;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.entities.room.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A class that implements EventPersistencePort which describes how
 * event data is saved and changed in the database
 */

@Component
public class EventPersistenceAdapter implements EventPersistencePort {
    private EventRepository eventRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;

    /**
     * Instantiates a EventPersistenceAdapter.
     *
     * @param eventRepository The event repository is where events are being saved.
     * @param userRepository The user repository is where users are being saved.
     * @param roomRepository The room repository is where rooms are being saved.
     */
    public EventPersistenceAdapter(EventRepository eventRepository, UserRepository userRepository,
                                   RoomRepository roomRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Saves the event to the database
     * @param event The event which is being saved.
     */
    @Override
    public void saveEvent(Event event) {
        Optional<RoomModel> roomModel = roomRepository.findByResourceId(event.getRoomId());
        if (roomModel.isPresent()) {
            EventModel eventModel = new EventModel(event.getId(), event.getName(), event.getDescription(),
                    event.getStartTime(), event.getEndTime(), roomModel.get(), event.getSpeakerLimit(),
                    event.getUserLimit(), event.isVipOnlyEvent());
            eventRepository.save(eventModel);
        }
    }

    /**
     * Finds an event id in the database.
     * @param eventId The event id which is searched for.
     * @return The event entity if one is found.
     */
    @Override
    public Optional<Event> findById(String eventId) {
        Optional<EventModel> eventModel = eventRepository.findByResourceId(eventId);
        return new EventMapper().mapModelToEntity(eventModel);
    }

    /**
     * Gets a list of all events at the conference.
     * @return A list of all events at the conference.
     */
    @Override
    public List<Event> getAllEvents() {
        EventMapper mapper = new EventMapper();
        return eventRepository.findAll().stream()
                                        .map(eventModel -> mapper.mapModelToEntity(Optional.of(eventModel)).get())
                                        .collect(Collectors.toList());
    }

    /**
     * Registers a user to an event by their user id.
     * @param eventId The event id which a user is being added to.
     * @param userId The user id which is added to the event.
     */
    @Override
    public void registerUserById(String eventId, String userId) {
        Optional<EventModel> eventModel = eventRepository.findByResourceId(eventId);
        Optional<UserModel> userModel = userRepository.findByResourceId(userId);
        if (eventModel.isPresent() && userModel.isPresent()) {
            eventModel.get().getUsers().add(userModel.get());
            eventRepository.flush();
        }
    }

    /**
     * Registers a speaker to an event.
     * @param eventId The event id in which the speaker is added to.
     * @param userId The speaker id that is added to an event.
     */
    @Override
    public void registerSpeakerById(String eventId, String userId) {
        Optional<EventModel> eventModel = eventRepository.findByResourceId(eventId);
        Optional<UserModel> userModel = userRepository.findByResourceId(userId);
        if (eventModel.isPresent() && userModel.isPresent()) {
            eventModel.get().getSpeakers().add(userModel.get());
            eventRepository.flush();
        }
    }

}

