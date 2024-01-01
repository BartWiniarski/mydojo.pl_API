package pl.mydojo.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import pl.mydojo.app.dto.ScheduleDTO;
import pl.mydojo.app.dto.TrainingGroupDTO;
import pl.mydojo.app.dto.VenueDTO;
import pl.mydojo.app.entities.DayOfWeek;
import pl.mydojo.app.entities.Role;
import pl.mydojo.app.entities.RoleType;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.repositories.RoleRepository;
import pl.mydojo.app.services.ScheduleService;
import pl.mydojo.app.services.TrainingGroupService;
import pl.mydojo.app.services.UserService;
import pl.mydojo.app.services.VenueService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PrepareDb {

    private final UserService userService;
    private final RoleRepository roleRepository;

    private final TrainingGroupService trainingGroupService;
    private final VenueService venueService;
    private final ScheduleService scheduleService;

    @Autowired
    public PrepareDb(UserService userService,
                     RoleRepository roleRepository,
                     TrainingGroupService trainingGroupService,
                     VenueService venueService,
                     ScheduleService scheduleService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.trainingGroupService = trainingGroupService;
        this.venueService = venueService;
        this.scheduleService = scheduleService;
    }

    public void setRoles() {
        roleRepository.save(new Role(RoleType.ADMIN));
        roleRepository.save(new Role(RoleType.TRAINER));
        roleRepository.save(new Role(RoleType.STUDENT));
    }

    public void setBasicAdmin() {
        List<Role> assignedRoles = new ArrayList<>() {{
            add(new Role(1L, RoleType.ADMIN));
        }};

        User user = User.builder()
                .firstName("Adam")
                .lastName("Nowak")
                .dob(LocalDate.of(1988, 10, 13))
                .email("admin@admin")
                .password(BCrypt.hashpw("admin", BCrypt.gensalt(10)))
                .roles(assignedRoles)
                .build();

        userService.addNewUser(user);
    }

    public void setBasicTrainer() {
        List<Role> assignedRoles = new ArrayList<>() {{
            add(new Role(2L, RoleType.TRAINER));
        }};

        User user = User.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .dob(LocalDate.of(1978, 3, 19))
                .email("trener@trener")
                .password(BCrypt.hashpw("trener", BCrypt.gensalt(10)))
                .roles(assignedRoles)
                .build();

        userService.addNewUser(user);
    }

    public void setBasicStudent() {
        List<Role> assignedRoles = new ArrayList<>() {{
            add(new Role(3L, RoleType.STUDENT));
        }};

        User user = User.builder()
                .firstName("Agata")
                .lastName("Kowalska")
                .dob(LocalDate.of(2003, 11, 8))
                .email("student@student")
                .password(BCrypt.hashpw("student", BCrypt.gensalt(10)))
                .roles(assignedRoles)
                .build();

        userService.addNewUser(user);
    }

    public void setBasicVenue() {
        VenueDTO venueDTO = VenueDTO.builder()
                .name("Dojo")
                .address("aleja Testowa 11, 50-312 Wrocław")
                .build();

        venueService.addNewVenue(venueDTO);
    }

    public void setBasicTrainingGroup() {
        TrainingGroupDTO trainingGroupDTO = TrainingGroupDTO.builder()
                .name("Grupa podstawowa")
                .description("Grupa początkująca")
                .build();

        trainingGroupService.addNewTrainingGroup(trainingGroupDTO);
    }

    public void setBasicSchedule() {
        ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .time(LocalTime.of(20, 0, 0))
                .venueId(1L)
                .trainingGroupId(1L)
                .build();

        scheduleService.addNewSchedule(scheduleDTO);
    }
}
