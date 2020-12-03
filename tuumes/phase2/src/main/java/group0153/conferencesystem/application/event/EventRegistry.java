//package group0153.conferencesystem.application.event;
//import group0153.conferencesystem.entities.event.Event;
//
//import java.util.ArrayList;
//
///**
// * Handles the registration of users for events (from the events perspective).
// */
//public class EventRegistry {
//    private final ArrayList<Event> events;
//
//    /**
//     *
//     * @param events The list of events that are currently scheduled.
//     */
//    public EventRegistry(ArrayList<Event> events) {
//        this.events = events;
//    }
//
//    /**
//     *
//     * @param userId The id of the user to be registered.
//     * @param eventId The id of the event that the user is to be registered for.
//     * @return One of:
//     * "User is already registered."
//     * "User has been registered successfully."
//     * "This event's user limit has been reached already."
//     * "No such event exists."
//     * The above return values mean what they depict.
//     */
//    public String registerUserForEvent(String userId, String eventId) {
//        for (Event event : this.events) {
//            if (event.getId().equals(eventId)) {
//                ArrayList<String> userIds = event.getUserIds();
//                for (String id : userIds) {
//                    if (id.equals(userId)) return "User is already registered.";
//                }
//                if (event.getUserCount() < event.getUserLimit()) {
//                    event.addUserId(userId);
//                    event.increaseUserCount(1);
//                    return "User has been registered successfully.";
//                } else {
//                    return "This event's user limit has been reached already.";
//                }
//            }
//        }
//        return "No such event exists.";
//    }
//
//    public String registerSpeakerForEvent(String speakerId, String eventId) {
//        for (Event event : this.events) {
//            if (event.getId().equals(eventId)) {
//                ArrayList<String> speakerIds = event.gets();
//                for (String id : speakerIds) {
//                    if (id.equals(userId)) return "User is already registered.";
//                }
//                if (event.getUserCount() < event.getUserLimit()) {
//                    event.addUserId(userId);
//                    event.increaseUserCount(1);
//                    return "User has been registered successfully.";
//                } else {
//                    return "This event's user limit has been reached already.";
//                }
//            }
//        }
//        return "No such event exists.";
//    }
//}
