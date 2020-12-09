package group0153.conferencesystem.adapters.controllers.user;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseData;
import group0153.conferencesystem.application.user.UserAuthManager;
import group0153.conferencesystem.application.user.data.*;
import group0153.conferencesystem.application.user.exception.IncorrectLoginException;
import group0153.conferencesystem.application.user.exception.UserExistsException;
import group0153.conferencesystem.entities.user.UserType;
import group0153.conferencesystem.exceptions.InvalidInputException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
    private final UserAuthManager userAuthManager;

    public UserController(UserAuthManager userAuthManager) {
        this.userAuthManager = userAuthManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserRegisterResource user) {
        try {
            UserRegisterData registerData = userAuthManager.create(user.getName(), user.getEmail(), user.getPassword(),
                    UserType.ATTENDEE);
            ResponseData response = new ResponseData(true, registerData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(new Response(false, "EMAIL_IN_USE"), HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody UserLoginResource user) {
        try {
            UserLoginData loginData = userAuthManager.login(user.getEmail(), user.getPassword());
            ResponseData response = new ResponseData(true, loginData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IncorrectLoginException e) {
            return new ResponseEntity<>(new Response(false, "WRONG_EMAIL_PASS"), HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
