package group0153.conferencesystem.adapters.gateways.event;

import group0153.conferencesystem.entities.event.Event;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for passing the event entity's information to the frontend.
 */
@Entity
public class EventModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String eventName;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomId;
    private ArrayList<String> speakerIds;
    private int userLimit;
    private boolean isVipOnlyEvent;

    public EventModel(String resourceId,
                      String eventName,
                      String description,
                      LocalDateTime startTime,
                      LocalDateTime endTime,
                      String roomId,
                      ArrayList<String> speakerIds,
                      int userLimit,
                      boolean vipOnlyEvent){
        this.resourceId = resourceId;
        this.eventName = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.speakerIds = speakerIds;
        this.userLimit = userLimit;
        this.isVipOnlyEvent = vipOnlyEvent;
    }

    public String getResourceId(){
        return resourceId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
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

    public String getRoomId() { return roomId;
    }
}
