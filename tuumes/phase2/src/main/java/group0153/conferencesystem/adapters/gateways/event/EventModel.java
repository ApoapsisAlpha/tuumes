package group0153.conferencesystem.adapters.gateways.event;

import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for passing the event entity's information to the frontend.
 */
@Entity
public class EventModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String eventName;
    private String eventType;
    private String description;
    private Date startTime;
    private Date endTime;
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
        try {
            this.speakerIds = event.getSpeakerIds();
        } catch (UnsuccessfulCommandException ex) {
            this.speakerIds = new ArrayList<String>();
        }
        this.userLimit = event.getUserLimit();
        this.userCount = event.getUserCount();
        this.userIds = event.getUserIds();
        this.isVipOnlyEvent = event.isVipOnlyEvent();
        this.eventType = event.getEventType();
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

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
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

    public String getEventType() {
        return eventType;
    }
}
