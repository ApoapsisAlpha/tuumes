package group0153.conferencesystem.application.event.data;

import group0153.conferencesystem.application.Data;

public class EventData implements Data {
    private String id;
    private String name;
    private String description;
    private int startTime;
    private int endTime;
    private boolean VIPOnly;
    private int limit;

    private String roomName;
    private int roomCapacity;

    public EventData(String id, String name, String description, int startTime, int endTime, boolean VIPOnly,
                     int limit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.VIPOnly = VIPOnly;
        this.limit = limit;
    }

    /**
     * Gets description.
     *
     * @return Value of description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets startTime.
     *
     * @return Value of startTime.
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets VIPOnly.
     *
     * @return Value of VIPOnly.
     */
    public boolean isVIPOnly() {
        return VIPOnly;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets endTime.
     *
     * @return Value of endTime.
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Gets roomName.
     *
     * @return Value of roomName.
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets new roomName.
     *
     * @param roomName New value of roomName.
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * Sets new roomCapacity.
     *
     * @param roomCapacity New value of roomCapacity.
     */
    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    /**
     * Gets roomCapacity.
     *
     * @return Value of roomCapacity.
     */
    public int getRoomCapacity() {
        return roomCapacity;
    }
}
