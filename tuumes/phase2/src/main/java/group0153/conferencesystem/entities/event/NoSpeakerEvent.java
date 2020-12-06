package group0153.conferencesystem.entities.event;

import java.util.Date;

public class NoSpeakerEvent extends Event {
    public NoSpeakerEvent(String id, String eventName, String description, Date startTime, Date endTime,
                          String room, int userLimit, boolean isVipOnlyEvent) {
        super(id, eventName, description, startTime, endTime, room, userLimit, isVipOnlyEvent);
    }
}
