package pl.mydojo.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mydojo.app.dto.UserProfileDTO;
import pl.mydojo.app.services.UserService;
import pl.mydojo.security.jwt.JwtService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private JwtService jwtService;
    private UserService userService;

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
    public ResponseEntity<?> postUserProfile(@RequestHeader("Authorization") String token,
                                             @RequestBody UserProfileDTO userProfileDTO) {

        String userEmailFromToken = jwtService.extractUsername(token.substring(7));
        userService.updateUserProfile(userEmailFromToken, userProfileDTO);

        return ResponseEntity.ok("User profile updated");
    }
}

