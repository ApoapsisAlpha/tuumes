package group0153.conferencesystem.adapters.controllers.room;

import group0153.conferencesystem.application.room.RoomManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller class that facilitates room creation and management as requested by the user
 */
@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomManager roomManager;

    /**
     * Construct an instance of RoomController using the provided roomManager
     *
     * @param roomManager a RoomManager instance that can manage room entities
     */
    public RoomController(RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    /**
     * Facilitate the creation of a new room specified by the user
     *
     * @param room a RoomResource instance that contains information about the new room
     * @return String id of the newly created room
     */
    @PostMapping("")
    String createRoom(@RequestBody RoomResource room) {
        return roomManager.createRoom(room.getName(), room.getCapacity());
    }
}
