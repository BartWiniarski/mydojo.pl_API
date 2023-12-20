package pl.mydojo.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mydojo.app.dto.*;
import pl.mydojo.app.entities.TrainingGroup;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.entities.Venue;
import pl.mydojo.app.services.DojoStatusService;
import pl.mydojo.app.services.TrainingGroupService;
import pl.mydojo.app.services.UserService;
import pl.mydojo.app.services.VenueService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final UserService userService;
    private final TrainingGroupService trainingGroupService;
    private final DojoStatusService dojoStatusService;
    private final VenueService venueService;

    public AdminController(UserService userService,
                           TrainingGroupService trainingGroupService,
                           DojoStatusService dojoStatusService,
                           VenueService venueService) {
        this.userService = userService;
        this.trainingGroupService = trainingGroupService;
        this.dojoStatusService = dojoStatusService;
        this.venueService = venueService;
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


    // --------------- USERS -------------------- \\
    @GetMapping("/users")
    public List<UserProfileAdminDTO> getUsers() {
        return userService.getUsersProfileAdmin();
    }

    @PostMapping("/users")
    public ResponseEntity<?> postUser(@RequestBody UserProfileAdminDTO userProfileAdminDTO) {
        User newUser = userService
                .addUserProfileAdmin(userProfileAdminDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User added with id: " + newUser.getId());
    }

    @GetMapping("/users/{id}")
    public UserProfileAdminDTO getUserById(@PathVariable Long id) {
        return userService.getUserProfileAdminById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?>  putUserById(@PathVariable Long id,
                            @RequestBody UserProfileAdminDTO userProfileAdminDTO) {
        userService.updateUserProfileAdminById(id, userProfileAdminDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User with id: " + id + " updated");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User with id: " + id + " deleted");
    }

    @PostMapping("/users/status/{id}")
    public ResponseEntity<?> postUserStatus(@PathVariable Long id) {
        String status = userService.userStatusChange(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("User with id: " + id + " status changed to: " + status + ".");
    }

// --------------- TRAINING GROUPS -------------------- \\

    @GetMapping("/trainingGroups")
    public List<TrainingGroupDTO> getTrainingGroups() {
        return trainingGroupService.getTrainingGroups();
    }

    @GetMapping("/trainingGroups/{id}")
    public TrainingGroupDTO getTrainingGroup(@PathVariable Long id) {
        return trainingGroupService.getTrainingGroupById(id);
    }

    @PostMapping("/trainingGroups")
    public ResponseEntity<?> postTrainingGroup(@RequestBody TrainingGroupDTO trainingGroupDTO) {
        TrainingGroup newTrainingGroup = trainingGroupService
                .addNewTrainingGroup(trainingGroupDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Training group added with ID: " + newTrainingGroup.getId());
    }

    @PutMapping("/trainingGroups/{id}")
    public ResponseEntity<?> putTrainingGroupById(@PathVariable Long id,
                                                       @RequestBody TrainingGroupDTO trainingGroupDTO) {
        trainingGroupService.updateTrainingGroupById(id, trainingGroupDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Training group with id: " + id + " updated");
    }

    @DeleteMapping("/trainingGroups/{id}")
    public ResponseEntity<?> deleteTrainingGroupById(@PathVariable Long id) {
        trainingGroupService.deleteTrainingGroupById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Training group with id: " + id + " deleted");
    }


    // --------------- TRAINERS -------------------- \\
    @GetMapping("/trainers")
    public List<TrainerProfileDTO> getTrainers() {

        return userService.getTrainersProfile();
    }

    // --------------- STUDENTS -------------------- \\
    @GetMapping("/students")
    public List<StudentProfileDTO> getStudents() {

        return userService.getStudentsProfile();
    }

    // --------------- DOJO STATUS -------------------- \\
    @GetMapping("/dojo/status")
    public DojoStatusDTO getDojoStatus(){
        return dojoStatusService.getDojoStatus();
    }

    // --------------- VENUES -------------------- \\
    @GetMapping("/venues")
    public List<VenueDTO> getVenuesList(){
        return venueService.getVenues();
    }
}
