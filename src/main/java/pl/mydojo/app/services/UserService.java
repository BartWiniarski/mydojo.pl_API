package pl.mydojo.app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.*;
import pl.mydojo.app.entities.Role;
import pl.mydojo.app.entities.RoleType;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.repositories.UserRepository;
import pl.mydojo.exceptions.user.UserAlreadyTakenException;
import pl.mydojo.exceptions.user.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserProfileDTOMapper userProfileDTOMapper;
    private final UserProfileAdminDTOMapper userProfileAdminDTOMapper;
    private final TrainerProfileDTOMapper trainerProfileDTOMapper;
    private final StudentProfileDTOMapper studentProfileDTOMapper;

    public UserService(UserRepository userRepository,
                       UserProfileDTOMapper userProfileDTOMapper,
                       UserProfileAdminDTOMapper userProfileAdminDTOMapper,
                       TrainerProfileDTOMapper trainerProfileDTOMapper,
                       StudentProfileDTOMapper studentProfileDTOMapper) {
        this.userRepository = userRepository;
        this.userProfileDTOMapper = userProfileDTOMapper;
        this.userProfileAdminDTOMapper = userProfileAdminDTOMapper;
        this.trainerProfileDTOMapper = trainerProfileDTOMapper;
        this.studentProfileDTOMapper = studentProfileDTOMapper;
    }

    //------------------ USER CRUD ------------------\\
    public void addNewUser(User user) {
        String email = user.getEmail();

        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new UserAlreadyTakenException(email);
        }
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {

        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public void deleteUserById(Long id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }


    //------------------ USER PROFILE DTO ------------------\\
    public UserProfileDTO getUserProfile(String userEmail) {
        User user = getUserByEmail(userEmail);
        return userProfileDTOMapper.apply(user);
    }

    public void updateUserProfile(String userEmail, UserProfileDTO userProfileDTO) {
        User user = getUserByEmail(userEmail);

        if (userProfileDTO.getFirstName() != null) {
            user.setFirstName(userProfileDTO.getFirstName());
        }
        if (userProfileDTO.getLastName() != null) {
            user.setLastName(userProfileDTO.getLastName());
        }
        if (userProfileDTO.getDob() != null) {
            user.setDob(userProfileDTO.getDob());
        }

        userRepository.save(user);
    }


    //------------------ ADMIN ------------------\\
    public List<UserProfileAdminDTO> getUsersProfileAdmin() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(u -> userProfileAdminDTOMapper.apply(u))
                .collect(Collectors.toList());
    }

    public User addUserProfileAdmin(UserProfileAdminDTO userProfileAdminDTO) {
        String email = userProfileAdminDTO.getEmail();

        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new UserAlreadyTakenException(email);
        }

        User user = User.builder()
                .firstName(userProfileAdminDTO.getFirstName())
                .lastName(userProfileAdminDTO.getLastName())
                .dob(userProfileAdminDTO.getDob())
                .email(userProfileAdminDTO.getEmail())
                .roles(userProfileAdminDTO.getRoles())
                .build();

        return userRepository.save(user);
    }

    public UserProfileAdminDTO getUserProfileAdminById(Long id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        User user = userRepository.findUserById(id);
        return userProfileAdminDTOMapper.apply(user);
    }

    public void updateUserProfileAdminById(Long id, UserProfileAdminDTO userProfileAdminDTO) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        User user = userRepository.findUserById(id);

        if (userProfileAdminDTO.getFirstName() != null) {
            user.setFirstName(userProfileAdminDTO.getFirstName());
        }
        if (userProfileAdminDTO.getLastName() != null) {
            user.setLastName(userProfileAdminDTO.getLastName());
        }
        if (userProfileAdminDTO.getDob() != null) {
            user.setDob(userProfileAdminDTO.getDob());
        }
        if (userProfileAdminDTO.getEmail() != null) {
            user.setEmail(userProfileAdminDTO.getEmail());
        }
        if (userProfileAdminDTO.getRoles() != null) {
            user.setRoles(userProfileAdminDTO.getRoles());
        }

        userRepository.save(user);
    }

    public List<TrainerProfileDTO> getTrainersProfile() {
        List<User> userTrainers = userRepository.findAllByRole(RoleType.TRAINER);

        return userTrainers
                .stream()
                .map(u -> trainerProfileDTOMapper.apply(u))
                .collect(Collectors.toList());
    }

    public List<StudentProfileDTO> getStudentsProfile() {
        List<User> userStudents = userRepository.findAllByRole(RoleType.STUDENT);

        return userStudents
                .stream()
                .map(u -> studentProfileDTOMapper.apply(u))
                .collect(Collectors.toList());
    }

    public String userStatusChange(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        User user = userRepository.findUserById(id);
        boolean currentStatus = user.getEnabled();
        user.setEnabled(!currentStatus);

        userRepository.save(user);

        return !currentStatus ? "enabled" : "disabled";
    }

    //------------------ MISCELLANEOUS ------------------\\
    public List<Role> getUserRoles(Long id) {
        return userRepository.findRolesByUserId(id);
    }


    //------------------SPRING SECURITY------------------\\
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getUserByEmail(email);
    }


}
