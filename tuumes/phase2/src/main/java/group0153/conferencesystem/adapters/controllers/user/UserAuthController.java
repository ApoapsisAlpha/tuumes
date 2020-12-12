package group0153.conferencesystem.adapters.controllers.user;

import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseData;
import group0153.conferencesystem.adapters.controllers.user.requests.UserLoginRequest;
import group0153.conferencesystem.adapters.controllers.user.requests.UserRegisterRequest;
import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.exceptions.InvalidInputException;
import group0153.conferencesystem.application.user.UserAuthManager;
import group0153.conferencesystem.application.user.exception.IncorrectLoginException;
import group0153.conferencesystem.application.user.exception.UserExistsException;
import group0153.conferencesystem.entities.user.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller class facilitating user registration and login.
 */
@RestController
@RequestMapping(value = "/users")
public class UserAuthController {
    private final UserAuthManager userAuthManager;

    /**
     * Construct an instance of UserAuthController using the provided UserAuthManager instance.
     *
     * @param userAuthManager Instance of UserAuthManager that can handle user login and registration
     */
    public UserAuthController(UserAuthManager userAuthManager) {
        this.userAuthManager = userAuthManager;
    }

    /**
     * Attempt to register an account for the user and display the result via a presenter and UI.
     *
     * @param user an instance of UserRegisterResource containing the user's information
     * @return Response entity with the data to be displayed and a status
     */
    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserRegisterRequest user) {
        try {
            UserType userType = UserType.ATTENDEE;
            if (user.getType().equalsIgnoreCase("Organizer")) {
                userType = UserType.ORGANIZER;
            } else if (user.getType().equalsIgnoreCase("Speaker")) {
                userType = UserType.SPEAKER;
            } else if (user.getType().equalsIgnoreCase("VIP")) {
                userType = UserType.VIP;
            }

            userAuthManager.create(user.getName(), user.getEmail(), user.getPassword(), userType);
            Response response = new Response(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(new Response(false, "EMAIL_IN_USE"), HttpStatus.OK);
        } catch (InvalidInputException e) {
            return new ResponseEntity<>(new Response(false, "BAD_INPUT"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Attempt to log the user in and display the result via a presenter and UI.
     *
     * @param user an instance of UserRegisterResource containing the user's information
     * @return Response entity with the data to be displayed and a status
     */
    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody UserLoginRequest user) {
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
