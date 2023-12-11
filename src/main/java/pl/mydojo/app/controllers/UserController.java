package pl.mydojo.app.controllers;

import pl.mydojo.app.entities.User;
import pl.mydojo.app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getUsersList() {
        return userService.getUsersList();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public void postUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/")
    public void putUser(@RequestBody User user){
        userService.updateUser(user);
    }
}
