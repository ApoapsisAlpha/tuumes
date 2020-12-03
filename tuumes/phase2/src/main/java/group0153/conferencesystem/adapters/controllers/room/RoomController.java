package group0153.conferencesystem.adapters.controllers.room;

import group0153.conferencesystem.application.room.RoomManager;
import group0153.conferencesystem.entities.room.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    Object test() {
        return new Room("dsfsdfg", "name", 69);
    }
}
