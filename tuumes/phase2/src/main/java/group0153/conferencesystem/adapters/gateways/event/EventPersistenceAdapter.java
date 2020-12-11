//package group0153.conferencesystem.adapters.gateways.event;
//
//import group0153.conferencesystem.application.event.EventPersistencePort;
//import group0153.conferencesystem.entities.event.Event;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//@Component
//public class EventPersistenceAdapter implements EventPersistencePort {
//    private  EventRepository eventRepository;
//
//    public EventPersistenceAdapter(EventRepository eventRepository){
//        this.eventRepository= eventRepository;
//    }
//
//    @Override
//    public void saveEvent(Event event) {
//        EventModel eventModel = new EventModel(
//                event.getId(),
//                event.getEventName(),
//                event.getDescription(),
//                event.getStartTime(),
//                event.getEndTime(),
//                event.getRoomId(),
//                event.getSpeakerIds(),
//                event.getUserLimit(),
//                event.isVipOnlyEvent());
//        eventRepository.save(eventModel);
//    }
//
//    @Override
//    public void deleteEvent(String eventId) {
//    }
//
//    @Override
//    public Optional<Event> getEvent(String eventId) {
//        return Optional.empty();
//    }
//
//    @Override
//    public ArrayList<Event> getAllEvents() {
//        return null;
//    }
//
//    @Override
//    public Optional<Event> findById(String eventId) {
//        Optional<EventModel> eventModel = eventRepository.findById(eventId);
//        if (eventModel.isPresent()) {
//            EventModel eventModelPresent = eventModel.get();
//            Event event = new Event(eventModelPresent.getResourceId(),
//                    eventModelPresent.getEventName(),
//                    eventModelPresent.getDescription(),
//                    eventModelPresent.getStartTime(),
//                    eventModelPresent.getEndTime(),
//                    eventModelPresent.getRoomId(),
//                    eventModelPresent.getSpeakerIds(),
//                    eventModelPresent.getUserLimit(),
//                    eventModelPresent.isVipOnlyEvent());
//            return Optional.of(event);
//        }
//        return Optional.empty();
//    }
//}
//
