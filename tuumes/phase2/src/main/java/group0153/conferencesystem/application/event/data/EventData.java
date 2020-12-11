package group0153.conferencesystem.application.event.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.room.data.RoomData;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;

public class EventData implements Data {
    final private String eventName;
    final private String description;
    final private long startTime;
    final private long endTime;
    final private ArrayList<String> speakerIds;
    final private int userLimit;
    final private int userCount;
    final private ArrayList<String> userIds;
    final private boolean isVipOnlyEvent;
    final private RoomData roomData;

    public EventData(Event event, RoomData roomData) {
        ArrayList<String> speakerIds1;
        this.eventName = event.getEventName();
        this.description = event.getDescription();
        this.startTime = event.getStartTime().getTime() / 1000;
        this.endTime = event.getEndTime().getTime() / 1000;
        speakerIds1 = event.getSpeakerIds();
        this.speakerIds = speakerIds1;
        this.userLimit = event.getUserLimit();
        this.userCount = event.getUserCount();
        this.userIds = event.getUserIds();
        this.isVipOnlyEvent = event.isVipOnlyEvent();
        this.roomData = roomData;
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

    public RoomData getRoomData() {
        return roomData;
    }
}
