package group0153.conferencesystem.adapters.controllers.room;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.adapters.controllers.room.requests.RoomRequest;
import group0153.conferencesystem.application.exceptions.InvalidInputException;
import group0153.conferencesystem.application.room.RoomManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A controller class that facilitates room creation and management as requested by the user
 */
@RestController
@RequestMapping(value = "/api/rooms")
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
     * Facilitate the request for every room within the conference.
     *
     * @return List of rooms available.
     */
    @GetMapping("/view")
    ResponseEntity<Response> getRooms() {
        return new ResponseEntity<>(new ResponseArray(true, roomManager.getRooms()), HttpStatus.OK);
    }

    /**
     * Facilitate the creation of a new room specified by the user
     *
     * @param room a RoomResource instance that contains information about the new room
     * @return String id of the newly created room
     */
    @PostMapping("/create")
    ResponseEntity<Response> createRoom(@RequestBody RoomRequest room) {
        try {
            roomManager.createRoom(room.getName(), room.getCapacity());
            return new ResponseEntity<>(new Response(true), HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
