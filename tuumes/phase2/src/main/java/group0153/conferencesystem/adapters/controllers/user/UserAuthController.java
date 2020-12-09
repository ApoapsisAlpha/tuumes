package group0153.conferencesystem.adapters.controllers.user;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseData;
import group0153.conferencesystem.adapters.controllers.user.resources.UserLoginResource;
import group0153.conferencesystem.adapters.controllers.user.resources.UserRegisterResource;
import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.user.UserAuthManager;
import group0153.conferencesystem.application.user.UserContactManager;
import group0153.conferencesystem.application.user.exception.IncorrectLoginException;
import group0153.conferencesystem.application.user.exception.UserExistsException;
import group0153.conferencesystem.entities.user.UserType;
import group0153.conferencesystem.exceptions.InvalidInputException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/user")
public class UserAuthController {
    private final UserAuthManager userAuthManager;

    public UserAuthController(UserAuthManager userAuthManager, UserContactManager userContactManager) {
        this.userAuthManager = userAuthManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserRegisterResource user) {
        try {
            userAuthManager.create(user.getName(), user.getEmail(), user.getPassword(), UserType.ATTENDEE);
            Response response = new Response(true);
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
            Data loginData = userAuthManager.login(user.getEmail(), user.getPassword());
            ResponseData response = new ResponseData(true, loginData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IncorrectLoginException e) {
            return new ResponseEntity<>(new Response(false, "WRONG_EMAIL_PASS"), HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
