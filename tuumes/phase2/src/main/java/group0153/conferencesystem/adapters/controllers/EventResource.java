package group0153.conferencesystem.adapters.controllers;

import group0153.conferencesystem.entities.Room;

import java.util.ArrayList;
import java.util.Date;

public class EventResource {
    private String eventName;
    private String description;
    private Date startTime;
    private Date endTime;
    private Room room;
    private ArrayList<String> speakerIds;
    private int userLimit;
    private boolean isVipOnlyEvent;

    public String getEventName(){
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<String> getSpeakerIds() {
        return speakerIds;
    }

    public int getUserLimit() {
        return userLimit;
    }

    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }
}
