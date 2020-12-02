package group0153.conferencesystem.entities;

import java.util.UUID;

public class Room {

    private final String id;
    private String name;
    private int capacity;

    public Room(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * set the name of the room
     * @param name name to set for the room
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set the capacity of the room
     * @param capacity capacity to set for the room
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the id of the room
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * @return the capacity of the room
     */
    public int getCapacity() {
        return capacity;
    }
}
