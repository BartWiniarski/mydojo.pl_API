package pl.mydojo.app.controllers;

import org.springframework.web.bind.annotation.*;
import pl.mydojo.app.dto.UserProfileAdminDTO;
import pl.mydojo.app.dto.UserProfileDTO;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.services.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public TestResponse testAdmin() {
        String role = "admin";
        LocalDateTime timeNow = LocalDateTime.now();
        String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                "sed do eiusmod tempor incididunt" +
                "ut labore et dolore magna aliqua. Laoreet suspendisse interdum" +
                "consectetur libero id faucibus nisl tincidunt. Orci ac auctor augue mauris augue neque gravida in." +
                " Tristique magna sit amet purus gravida. Nunc sed id semper risus. Convallis osuere" +
                "morbi leo urna. Morbi tincidunt augue interdum velit euismod in. Tempus urna et" +
                "pharetra. Lacus viverra vitae congue eu consequat. Sed odio morbi quis commodo" +
                "Lacus sed viverra tellus in hac habitasse platea dictumst vestibulum. Posuere" +
                "sollicitudin aliquam ultrices sagittis orci a scelerisque purus. Elementum" +
                "egestas sed sed risus pretium quam. Tincidunt nunc pulvinar sapien et ligula";

        return TestResponse.builder()
                .role(role)
                .time(timeNow)
                .loremIpsum(loremIpsum)
                .build();
    }

    @GetMapping("/users")
    public List<UserProfileAdminDTO> getUsers() {
        return userService.getUsersProfileAdmin();
    }

    @GetMapping("/users/{id}")
    public UserProfileAdminDTO getUserById(@PathVariable Long id) {
        return userService.getUserProfileAdminById(id);
    }

    @PutMapping("/users/{id}")
    public void putUserById(@PathVariable Long id,
                            @RequestBody UserProfileAdminDTO userProfileAdminDTO) {
        userService.updateUserProfileAdminById(id, userProfileAdminDTO);
    }

}
