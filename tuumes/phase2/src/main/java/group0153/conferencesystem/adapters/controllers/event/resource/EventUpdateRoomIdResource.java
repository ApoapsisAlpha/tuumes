package group0153.conferencesystem.adapters.controllers.event.resource;

public class EventUpdateRoomIdResource {
    private String eventId;
    private String roomId;

    public EventUpdateRoomIdResource(String eventId, String roomId){
        this.eventId = eventId;
        this.roomId = roomId;
    }

    /**
     * Get the event id.
     * @return id of the event.
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Get the room id.
     * @return id of the room of which the event take place in.
     */
    public String getRoomId() {
        return roomId;
    }
}
