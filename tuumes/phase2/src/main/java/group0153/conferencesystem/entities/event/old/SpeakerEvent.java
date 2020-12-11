//package group0153.conferencesystem.entities.event.old;
//
//import group0153.conferencesystem.application.exceptions.UnsuccessfulCommandException;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//public class SpeakerEvent extends Event {
//    private final ArrayList<String> speakerIds;
//    private int speakerLimit;
//    private final String eventType = "MultiSpeakerEvent";
//
//    public SpeakerEvent(String id, String eventName, String description, Date startTime, Date endTime,
//                        String room, int userLimit, boolean isVipOnlyEvent, ArrayList<String> speakerIds, int speakerLimit) {
//        super(id, eventName, description, startTime, endTime, room, userLimit, isVipOnlyEvent);
//        this.speakerLimit = speakerLimit;
//        this.speakerIds = speakerIds;
//    }
//
//    /**
//     * @param speakerId The id of the speaker to be added.
//     */
//    public void addSpeakerId(String speakerId) throws UnsuccessfulCommandException {
//        for (String otherSpeakerId : this.getSpeakerIds()) {
//            if (otherSpeakerId.equals(speakerId)) throw new UnsuccessfulCommandException("This speaker is already registered for this event.");
//        }
//        if (this.speakerIds.size() >= speakerLimit)
//            throw new UnsuccessfulCommandException("The maximum number of speakers has already been reached.");
//        this.speakerIds.add(speakerId);
//    }
//
//    public ArrayList<String> getSpeakerIds() { return this.speakerIds; }
//
//    /**
//     *
//     * @param speakerId The id of the speaker to be removed.
//     *                  If the speaker is not registered for this event, nothing happens.
//     */
//    public void removeSpeakerId(String speakerId) {
//        this.speakerIds.remove(speakerId);
//    }
//
//    /**
//     *
//     * @return MultiSpeakerEvent
//     */
//    public String getEventType() { return "MultiSpeakerEvent"; }
//}
