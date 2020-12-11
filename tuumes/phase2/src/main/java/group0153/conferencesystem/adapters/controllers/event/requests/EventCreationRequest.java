package group0153.conferencesystem.adapters.controllers.event.requests;

import java.time.LocalDateTime;

public class EventCreationRequest {
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomId;
    private int speakerLimit;
    private int userLimit;
    private boolean isVipOnlyEvent;

    public EventCreationRequest(String name, String description, LocalDateTime startTime, LocalDateTime endTime,
                                String roomId, int speakerLimit, int userLimit, boolean isVipOnlyEvent) {
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
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
