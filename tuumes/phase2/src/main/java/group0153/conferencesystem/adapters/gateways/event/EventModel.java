package group0153.conferencesystem.adapters.gateways.event;

import group0153.conferencesystem.entities.event.Event;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @ElementCollection
    private List<String> speakerIds;
    private int userLimit;
    private int userCount;
    @ElementCollection
    private List<String> userIds;
    private boolean isVipOnlyEvent;

    public EventModel(){}

    public EventModel(Event event, String resourceId) {
        this.resourceId = resourceId;
        this.eventName = event.getEventName();
        this.description = event.getDescription();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.speakerIds = event.getSpeakerIds();
        this.userLimit = event.getUserLimit();
        this.userCount = event.getUserCount();
        this.userIds = event.getUserIds();
        this.isVipOnlyEvent = event.isVipOnlyEvent();
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

    public List<String> getSpeakerIds() {
        return speakerIds;
    }

    public int getUserLimit() {
        return userLimit;
    }

    public int getUserCount() {
        return userCount;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }

}
