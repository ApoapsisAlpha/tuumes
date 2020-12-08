package group0153.conferencesystem.adapters.gateways.event;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class EventModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String eventName;
    private String description;
    private Date startTime;
    private Date endTime;
    private ArrayList<String> speakerIds;
    private int userLimit;
    private int userCount;
    private ArrayList<String> userIds;
    private boolean isVipOnlyEvent;

    public EventModel(){}

    public EventModel(String resourceId, String eventName, String description, Date startTime, Date endTime, ArrayList<String> speakerIds, int userLimit, boolean isVipOnlyEvent){
        this.resourceId = resourceId;
        this.eventName = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.speakerIds = speakerIds;
        this.userLimit = userLimit;
        this.userCount = 0;
        this.userIds = new ArrayList<>();
        this.isVipOnlyEvent = isVipOnlyEvent;
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
}
