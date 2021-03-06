package group0153.conferencesystem.application.room.data;

import group0153.conferencesystem.application.Data;

/**
 * A class for storing information of a room.
 */
public class RoomData implements Data {
    private final String id;
    private final String name;
    private final int capacity;

    /**
     * Construct a new instance of RoomData with the specified information
     *
     * @param id       the id of the room
     * @param name     the String name of the room
     * @param capacity the int total capacity of the room
     */
    public RoomData(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
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
     * Gets capacity.
     *
     * @return Value of capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public String getId() {
        return id;
    }
}
