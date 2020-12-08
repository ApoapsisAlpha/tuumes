package group0153.conferencesystem.adapters.controllers.user;

import group0153.conferencesystem.adapters.controllers.room.RoomResource;
import group0153.conferencesystem.application.room.RoomManager;
//import group0153.conferencesystem.application.user.UserAuthManager;
import group0153.conferencesystem.application.user.UserAuthManager;
import group0153.conferencesystem.application.user.exception.IncorrectLoginException;
import group0153.conferencesystem.application.user.exception.UserExistsException;
import group0153.conferencesystem.entities.user.User;
import group0153.conferencesystem.entities.user.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {
    private UserAuthManager userAuthManager;

    public UserController(UserAuthManager userAuthManager) {
        this.userAuthManager = userAuthManager;
    }

    @PostMapping("/user/register")
    String createUser(@RequestBody UserRegisterResource user) {
        try {
            return userAuthManager.create(user.getName(), user.getEmail(), user.getPassword(), UserType.ATTENDEE);
        } catch (Exception e) {
            return "ALREADY_EXISTS";
        }
    }

    @PostMapping("/user/login")
    User createRoom(@RequestBody UserLoginResource user) {
        try {
            return userAuthManager.login(user.getEmail(), user.getPassword());
        } catch (Exception e) {
            return "INCORRECT_LOGIN";
        }
    }

    @GetMapping("/test")
    public ResponseEntity<HashMap<String, String>> test(@RequestParam(value = "name") String name) {
        // TODO: 12/3/2020 get rid of this
        if (name.equals("makan")) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("you", "test");
            hashMap.put("me", "makan");
            return new ResponseEntity<>(hashMap, HttpStatus.FORBIDDEN);
        } else {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("you", name);
            hashMap.put("me", "makan");
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
        }
    }
}
