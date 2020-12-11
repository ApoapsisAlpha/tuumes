package group0153.conferencesystem.adapters.controllers.event.requests;

public class EventCreationRequest {
    private String name;
    private String description;
    private Long startTime;
    private Long endTime;
    private String roomId;
    private int speakerLimit;
    private int userLimit;
    private boolean isVipOnlyEvent;

    public EventCreationRequest(String name, String description, Long startTime, Long endTime, String roomId,
                                int speakerLimit, int userLimit, boolean isVipOnlyEvent) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.speakerLimit = speakerLimit;
        this.userLimit = userLimit;
        this.isVipOnlyEvent = isVipOnlyEvent;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getSpeakerLimit() {
        return speakerLimit;
    }

    public int getUserLimit() {
        return userLimit;
    }

    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }
}
