package group0153.conferencesystem.adapters.controllers.room;

import group0153.conferencesystem.application.room.RoomManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class RoomController {
    private RoomManager roomManager;

    public RoomController(RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    @PostMapping("/rooms")
    String createRoom(@RequestBody RoomResource room) {
        return roomManager.createRoom(room.getName(), room.getCapacity());
    }
}
