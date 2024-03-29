package pl.mydojo.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mydojo.app.dto.UserProfileDTO;
import pl.mydojo.app.services.UserService;
import pl.mydojo.security.jwt.JwtService;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;

    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public UserProfileDTO getUserProfile(@RequestHeader("Authorization") String token) {
        String userEmailFromToken = jwtService.extractUsername(token.substring(7));
        return userService.getUserProfile(userEmailFromToken);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> putUserProfile(@RequestHeader("Authorization") String token,
                                            @RequestBody UserProfileDTO userProfileDTO) {

        String userEmailFromToken = jwtService.extractUsername(token.substring(7));
        userService.updateUserProfile(userEmailFromToken, userProfileDTO);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("User profile updated");
    }
}

