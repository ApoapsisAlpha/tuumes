package group0153.conferencesystem.adapters.gateways.event;

import group0153.conferencesystem.adapters.gateways.room.RoomModel;
import group0153.conferencesystem.adapters.gateways.user.UserModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Database model for the event entity.
 */
@Entity
public class EventModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int speakerLimit;
    private int userLimit;
    private boolean isVipOnlyEvent;

    @ManyToOne
    private RoomModel room;

    @ManyToMany
    private Set<UserModel> speakers;

    @ManyToMany
    private Set<UserModel> users;

    public EventModel() {

    }

    public EventModel(String resourceId, String name, String description, LocalDateTime startTime, LocalDateTime endTime,
                      RoomModel room, int speakerLimit, int userLimit, boolean isVipOnlyEvent) {
        this.resourceId = resourceId;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.userLimit = userLimit;
        this.speakerLimit = speakerLimit;
        this.isVipOnlyEvent = isVipOnlyEvent;
    }

    public String getResourceId(){
        return resourceId;
    }

    public String getName() {
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

    public RoomModel getRoom() {
        return room;
    }

    public int getSpeakerLimit() {
        return speakerLimit;
    }

    public Set<UserModel> getSpeakers() {
        return speakers;
    }

    public Set<UserModel> getUsers() {
        return users;
    }
}
