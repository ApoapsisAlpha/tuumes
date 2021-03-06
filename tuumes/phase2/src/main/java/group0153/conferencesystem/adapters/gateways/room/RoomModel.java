package group0153.conferencesystem.adapters.gateways.room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * An entity class for a room to be stored in the database
 */
@Entity
public class RoomModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String name;
    private int capacity;

    /**
     * Construct an instance of RoomModel with the given information
     *
     * @param resourceId the id of the room
     * @param name       the name of the room
     * @param capacity   the user capacity of the room
     */
    public RoomModel(String resourceId, String name, int capacity) {
        this.resourceId = resourceId;
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * Construct an instance of RoomModel
     */
    public RoomModel() {

    }

    /**
     * Get id of room
     *
     * @return string id of the room
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Get name of room
     * @return name of room
     */
    public String getName() {
        return name;
    }

    /**
     * Get the capacity of the room
     *
     * @return int value of the capacity of the room
     */
    public int getCapacity() {
        return capacity;
    }
}
