package pl.mydojo.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mydojo.app.dto.*;
import pl.mydojo.app.entities.Schedule;
import pl.mydojo.app.entities.TrainingGroup;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.entities.Venue;
import pl.mydojo.app.services.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/admin")
public class AdminController {

    private final UserService userService;
    private final TrainingGroupService trainingGroupService;
    private final DojoStatusService dojoStatusService;
    private final VenueService venueService;
    private final ScheduleService scheduleService;

    public AdminController(UserService userService,
                           TrainingGroupService trainingGroupService,
                           DojoStatusService dojoStatusService,
                           VenueService venueService,
                           ScheduleService scheduleService) {
        this.userService = userService;
        this.trainingGroupService = trainingGroupService;
        this.dojoStatusService = dojoStatusService;
        this.venueService = venueService;
        this.scheduleService = scheduleService;
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
    public ResponseEntity<?> putUserById(@PathVariable Long id,
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
    public DojoStatusDTO getDojoStatus() {
        return dojoStatusService.getDojoStatus();
    }

    // --------------- VENUES -------------------- \\
    @GetMapping("/venues")
    public List<VenueDTO> getVenues() {
        return venueService.getVenues();
    }

    @GetMapping("/venues/{id}")
    public VenueDTO getVenue(@PathVariable long id) {
        return venueService.getVenue(id);
    }

    @PostMapping("/venues")
    public ResponseEntity<?> postVenue(@RequestBody VenueDTO venueDTO) {
        Venue newVenue = venueService.addNewVenue(venueDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Venue added with id: " + newVenue.getId());
    }

    @PutMapping("/venues/{id}")
    public ResponseEntity<?> putVenueById(@PathVariable Long id,
                                          @RequestBody VenueDTO venueDTO) {
        venueService.updateVenueById(id, venueDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Venue with id: " + id + " updated");
    }

    @DeleteMapping("/venues/{id}")
    public ResponseEntity<?> deleteVenueById(@PathVariable Long id) {
        venueService.deleteVenueById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Venue with id: " + id + " deleted");
    }

    // --------------- SCHEDULES -------------------- \\
    @GetMapping("/schedules")
    public List<ScheduleDTO> getSchedules() {
        return scheduleService.getSchedules();
    }

    @GetMapping("/schedules/{id}")
    public ScheduleDTO getSchedule(@PathVariable long id) {
        return scheduleService.getScheduleById(id);
    }

    @PostMapping("/schedules")
    public ResponseEntity<?> postSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule newSchedule = scheduleService.addNewSchedule(scheduleDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Schedule added with id: " + newSchedule.getId());
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<?> putSchedule(@PathVariable long id,
                                         @RequestBody ScheduleDTO scheduleDTO) {

        scheduleService.updateScheduleById(id, scheduleDTO);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Schedule with id: " + id + " updated");
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<?> deleteScheduleById(@PathVariable Long id) {
        scheduleService.deleteScheduleById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Schedule with id: " + id + " deleted");
    }

}
