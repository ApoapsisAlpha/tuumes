package group0153.conferencesystem.application.room.data;

import group0153.conferencesystem.application.Data;

public class RoomData implements Data {
    private String name;
    private int capacity;

    public RoomData(String name, int capacity) {
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
}
