package group0153.conferencesystem.application;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class for building and returning an event.
 */
public class EventBuilder {
    private String eventType;
    private String id;                      // id of this event.
    private String eventName;                     // name of the event
    private String description;                   // description of the event.
    private Date startTime;                       // start time of event.
    private Date endTime;                         // end time of event.
    private String roomId;                            // room number where the event take place.
    private int userLimit;                        // maximum amount of people allowed at this event.
    private int userCount;                        // number of people currently scheduled to go to this event.
    private ArrayList<String> userIds;      // list of ids of the users registered to this event.
    private boolean isVipOnlyEvent;               // whether this event is VIP-only.

    void setId(String id) { this.id = id; }
    void setEventName(String eventName) { this.eventName = eventName; }
    void setDescription (String description) { this.description = description; }
    void setStartTime(Date startTime) { this.startTime = startTime; }
    void setEndTime(Date endTime) { this.endTime = endTime; }
    void setRoomId(String roomId) { this.roomId = roomId; }
    void setUserLimit(int userLimit) { this.userLimit = userLimit; }
    void setUserCount(int userCount) { this.userCount = userCount; }
    void setUserIds(ArrayList<String> userIds) { this.userIds = userIds; }
    void setIsVipOnlyEvent(boolean isVipOnlyEvent) { this.isVipOnlyEvent = isVipOnlyEvent; }
}
