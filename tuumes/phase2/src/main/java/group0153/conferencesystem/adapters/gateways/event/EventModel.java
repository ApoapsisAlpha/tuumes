package group0153.conferencesystem.adapters.gateways.event;

import group0153.conferencesystem.adapters.gateways.user.UserModel;
import group0153.conferencesystem.entities.event.Event;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Class for passing the event entity's information to the frontend.
 */
@Entity
public class EventModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomId;
    private int userLimit;
    private boolean isVipOnlyEvent;

    @ManyToMany
    private Set<UserModel> speakers;

    public EventModel() {

    }

    public EventModel(String resourceId,
                      String eventName,
                      String description,
                      LocalDateTime startTime,
                      LocalDateTime endTime,
                      String roomId,
                      int userLimit,
                      boolean vipOnlyEvent){
        this.resourceId = resourceId;
        this.name = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.userLimit = userLimit;
        this.isVipOnlyEvent = vipOnlyEvent;
    }

    public String getResourceId(){
        return resourceId;
    }

    public String getEventName() {
        return name;
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

    public Set<UserModel> getSpeaker() {
        return speakers;
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
