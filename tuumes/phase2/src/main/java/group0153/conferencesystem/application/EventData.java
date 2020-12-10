package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class EventData implements Data {
    private final String eventName;
    private final String eventType;
    private final String description;
    private final long startTime;
    private final long endTime;
    private final ArrayList<String> speakerIds;
    private final int userLimit;
    private final int userCount;
    private final ArrayList<String> userIds;
    private final boolean isVipOnlyEvent;

    public EventData(Event event) {
        ArrayList<String> speakerIds1;
        this.eventName = event.getEventName();
        this.description = event.getDescription();
        this.startTime = event.getStartTime().getTime() / 1000;
        this.endTime = event.getEndTime().getTime() / 1000;
        try {
            speakerIds1 = event.getSpeakerIds();
        } catch (UnsuccessfulCommandException ex) {
            speakerIds1 = new ArrayList<String>();
        }
        this.speakerIds = speakerIds1;
        this.userLimit = event.getUserLimit();
        this.userCount = event.getUserCount();
        this.userIds = event.getUserIds();
        this.isVipOnlyEvent = event.isVipOnlyEvent();
        this.eventType = event.getEventType();
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public ArrayList<String> getSpeakerIds() {
        return speakerIds;
    }

    public int getUserLimit() {
        return userLimit;
    }

    public int getUserCount() {
        return userCount;
    }

    public ArrayList<String> getUserIds() {
        return userIds;
    }

    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }

    public String getEventType() {
        return eventType;
    }
}
