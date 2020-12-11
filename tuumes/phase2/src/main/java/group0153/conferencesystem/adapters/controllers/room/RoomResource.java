package group0153.conferencesystem.adapters.controllers.room;

/**
 * A class facilitating the user's interaction with rooms by providing the information about them
 */
public class RoomResource {
    private String name;
    private int capacity;

    //TODO: assign variables in constructor? not sure why they aren't assigned anywhere

    /**
     * Get name of room
     *
     * @return String value of name
     */
    public String getName() {
        return name;
    }

    /**
     *  Get capacity of room
     *
     * @return int capacity of room
     */
    public int getCapacity() {
        return capacity;
    }
}
