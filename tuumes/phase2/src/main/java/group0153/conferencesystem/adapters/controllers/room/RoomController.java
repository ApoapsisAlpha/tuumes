package group0153.conferencesystem.adapters.controllers.room;

import group0153.conferencesystem.application.Response;
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

    @GetMapping("/test")
    public ResponseEntity<HashMap<String, String>> test(@RequestParam(value = "name") String name) {
        // TODO: 12/3/2020 get rid of this 
        if (name.equals("makan")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("you", name);
            hashMap.put("me", "makan");
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
        }
    }
}
