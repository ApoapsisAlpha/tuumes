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
    private @Id
    @GeneratedValue
    Long id;
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

    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<UserModel> users;

    /**
     * Construct a new instance of EventModel.
     */
    public EventModel() {

    }

    /**
     * Construct a new instance of EventModel using the provided information.
     *
     * @param resourceId     the database id of the event
     * @param name           the name of the event
     * @param description    the description of the event
     * @param startTime      the start time of the event
     * @param endTime        the end time of the event
     * @param room           the RoomModel representing the room where the event takes place
     * @param speakerLimit   the int maximum number of speakers allowed at the event
     * @param userLimit      the int maximum number of users allowed to be registered for the event
     * @param isVipOnlyEvent boolean whether the event is for VIPs only
     */
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

    /**
     * Get the resource/database id of the event.
     *
     * @return String resource/database id of the event
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Get the name of the event.
     *
     * @return String name of the event
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description of the event.
     *
     * @return String description of the event
     */
    public String getDescription() {
        return description;
    }


    /**
     * Get the start time of the event.
     *
     * @return LocalDateTime start time of the event
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Get the end time of the event.
     *
     * @return LocalDateTime end time of the event
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Get the set of UserModels that are registered to be the speakers of the event.
     *
     * @return Set of UserModels representing the speakers of the event
     */
    public Set<UserModel> getSpeaker() {
        return speakers;
    }

    /**
     * Get the maximum users allowed to register for the event.
     *
     * @return int the user limit for the event
     */
    public int getUserLimit() {
        return userLimit;
    }

    /**
     * Get whether the event is a VIP only event.
     *
     * @return boolean stating if the event is for VIPs only
     */
    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }

    /**
     * Get the RoomModel of where the event is going to occur.
     *
     * @return RoomModel representing the room where the event is scheduled to occur
     */
    public RoomModel getRoom() {
        return room;
    }

    /**
     * Get the limit to the number of speakers allowed to speak at the event.
     *
     * @return int representing the maximum number of speakers allowed at the event
     */
    public int getSpeakerLimit() {
        return speakerLimit;
    }

    /**
     * Get the set of UserModels containing the data of speakers that are scheduled to speak at the event.
     *
     * @return Set of UserModels representing Speakers that are to speak at the event
     */
    public Set<UserModel> getSpeakers() {
        return speakers;
    }

    /**
     * Get the set of UserModels containing the data of users that are scheduled to attend the event.
     *
     * @return Set of UserModels representing users that are to attend the event
     */
    public Set<UserModel> getUsers() {
        return users;
    }
}
