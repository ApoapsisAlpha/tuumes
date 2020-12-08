package group0153.conferencesystem.adapters.controllers.user;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseData;
import group0153.conferencesystem.application.user.UserAuthManager;
import group0153.conferencesystem.application.user.data.UserLoginData;
import group0153.conferencesystem.application.user.data.UserRegisterData;
import group0153.conferencesystem.entities.user.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserAuthManager userAuthManager;

    public UserController(UserAuthManager userAuthManager) {
        this.userAuthManager = userAuthManager;
    }

    @PostMapping("/user/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserRegisterResource user) {
        try {
            UserRegisterData registerData = userAuthManager.create(user.getName(), user.getEmail(), user.getPassword(),
                    UserType.ATTENDEE);
            ResponseData response = new ResponseData(true, registerData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, "EMAIL_IN_USE"), HttpStatus.OK);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<Response> loginUser(@RequestBody UserLoginResource user) {
        try {
            UserLoginData loginData = userAuthManager.login(user.getEmail(), user.getPassword());
            ResponseData response = new ResponseData(true, loginData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(false, e.getMessage()), HttpStatus.OK);
        }
    }
}
