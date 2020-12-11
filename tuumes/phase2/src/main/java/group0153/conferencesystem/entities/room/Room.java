package group0153.conferencesystem.entities.room;

/**
 * An entity class for room.
 */
public class Room {

    private final String id;
    private String name;
    private int capacity;

    /**
     * Construct a new instance of Room with the provided information.
     *
     * @param id       the String id of the Room.
     * @param name     the String name of the Room.
     * @param capacity the int total capacity of the Room.
     */
    public Room(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
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
     * set the name of the room
     *
     * @param name name to set for the room
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the capacity of the room
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * set the capacity of the room
     *
     * @param capacity capacity to set for the room
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
