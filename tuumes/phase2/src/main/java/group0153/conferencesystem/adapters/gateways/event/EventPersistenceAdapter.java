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

@Component
public class EventPersistenceAdapter implements EventPersistencePort {
    private EventRepository eventRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;

    public EventPersistenceAdapter(EventRepository eventRepository, UserRepository userRepository,
                                   RoomRepository roomRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void saveEvent(Event event) {
        RoomModel roomModel = roomRepository.findByResourceId(event.getRoomId());
        EventModel eventModel = new EventModel(event.getId(), event.getName(), event.getDescription(),
                event.getStartTime(), event.getEndTime(), roomModel, event.getSpeakerLimit(),
                event.getUserLimit(), event.isVipOnlyEvent());

        eventRepository.save(eventModel);
    }

    @Override
    public Optional<Event> findById(String eventId) {
        Optional<EventModel> eventModel = eventRepository.findByResourceId(eventId);
        return new EventMapper().mapModelToEntity(eventModel);
    }

    @Override
    public List<Event> getAllEvents() {
        EventMapper mapper = new EventMapper();
        return eventRepository.findAll().stream()
                                        .map(eventModel -> mapper.mapModelToEntity(Optional.of(eventModel)).get())
                                        .collect(Collectors.toList());
    }

    @Override
    public void registerUserById(String eventId, String userId) {
        Optional<EventModel> eventModel = eventRepository.findByResourceId(eventId);
        Optional<UserModel> userModel = userRepository.findByResourceId(userId);
        if (eventModel.isPresent() && userModel.isPresent()) {
            eventModel.get().getUsers().add(userModel.get());
            eventRepository.flush();
        }
    }

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

