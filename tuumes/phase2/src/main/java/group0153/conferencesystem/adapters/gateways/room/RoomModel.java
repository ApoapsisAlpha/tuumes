package group0153.conferencesystem.adapters.gateways.room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RoomModel {
    private @Id @GeneratedValue Long id;
    private String resourceId;
    private String name;
    private int capacity;

    public RoomModel(String resourceId, String name, int capacity) {
        this.resourceId = resourceId;
        this.name = name;
        this.capacity = capacity;
    }

    public String getResourceId() {
        return resourceId;
    }

    public int getCapacity() {
        return capacity;
    }
}
