package group0153.conferencesystem.adapters.controllers.room.requests;

import group0153.conferencesystem.application.exceptions.InvalidInputException;

/**
 * A class facilitating the user's interaction with rooms by providing the information about them
 */
public class RoomRequest {
    private String name;
    private int capacity;

    /**
     * Constructs the RoomRequest.
     * @param name Name of the room
     * @param capacity Capacity of the room
     */
    public RoomRequest(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * Get name of room
     *
     * @return String value of name
     */
    public String getName() throws InvalidInputException {
        if(name.isEmpty())
            throw new InvalidInputException("name");

        return name;
    }

    /**
     *  Get capacity of room
     *
     * @return int capacity of room
     */
    public int getCapacity() throws InvalidInputException {
        if(capacity <= 0)
            throw new InvalidInputException("capacity");

        return capacity;
    }
}
