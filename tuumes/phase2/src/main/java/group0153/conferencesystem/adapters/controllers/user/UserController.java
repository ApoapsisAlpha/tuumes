package group0153.conferencesystem.adapters.controllers.user;


import group0153.conferencesystem.adapters.controllers.Response;
import group0153.conferencesystem.adapters.controllers.ResponseArray;
import group0153.conferencesystem.application.user.UserManager;
import group0153.conferencesystem.application.user.data.UserContactData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for general user requests.
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserManager userManager;

    /**
     * Construct an instance of UserController using the provided UserManager instance.
     *
     * @param userManager UserManager instance
     */
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Gets all speakers
     * @return a response containing all speakers
     */
    @GetMapping("/speakers")
    public ResponseEntity<Response> getAllSpeakers() {
        List<UserContactData> speakers = userManager.getSpeakers();
        return new ResponseEntity<>(new ResponseArray(true, speakers), HttpStatus.OK);
    }
}
