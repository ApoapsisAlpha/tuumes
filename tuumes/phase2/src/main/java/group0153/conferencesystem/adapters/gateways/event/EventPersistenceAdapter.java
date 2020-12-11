//package group0153.conferencesystem.adapters.gateways.event;
//
//import group0153.conferencesystem.application.event.EventPersistencePort;
//import group0153.conferencesystem.entities.event.old.Event;
//import org.springframework.stereotype.Component;
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
//                event.getSpeakerIds(),
//                event.getUserLimit(),
//                event.isVipOnlyEvent());
//        eventRepository.save(eventModel);
//    }
//}
