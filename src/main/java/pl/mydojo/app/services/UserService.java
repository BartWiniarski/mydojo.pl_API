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

import java.util.List;
import java.util.Optional;
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

        Optional<User> userByEmail =
                userRepository.findUserByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new IllegalStateException("User with provided e-mail already exists.");
        }
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public User getUserByEmail(String email) {
        final String USER_WITH_E_MAIL_NOT_FOUND = "User with e-mail %s not found";

        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_WITH_E_MAIL_NOT_FOUND, email)));
        //TODO exception handling
    }

    public void updateUser(User user) {
        Long id = user.getId();
        boolean exists = userRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("User with provided ID: " + id + " does not exists.");
        }
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        boolean exists = userRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("User with provided ID: " + id + " does not exists.");
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

        updateUser(user);
    }


    //------------------ ADMIN ------------------\\
    public List<UserProfileAdminDTO> getUsersProfileAdmin() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(u -> userProfileAdminDTOMapper.apply(u))
                .collect(Collectors.toList());
    }

    public void addUserProfileAdmin(UserProfileAdminDTO userProfileAdminDTO) {
        Optional<User> userByEmail =
                userRepository.findUserByEmail(userProfileAdminDTO.getEmail());

        if (userByEmail.isPresent()) {
            throw new IllegalStateException("User with provided e-mail already exists.");
        }

        User user = User.builder()
                .firstName(userProfileAdminDTO.getFirstName())
                .lastName(userProfileAdminDTO.getLastName())
                .dob(userProfileAdminDTO.getDob())
                .email(userProfileAdminDTO.getEmail())
                .roles(userProfileAdminDTO.getRoles())
                .build();

        userRepository.save(user);
    }

    public UserProfileAdminDTO getUserProfileAdminById(Long id) {
        User user = userRepository.findUserById(id);

        return userProfileAdminDTOMapper.apply(user);
    }

    public void updateUserProfileAdminById(Long id, UserProfileAdminDTO userProfileAdminDTO) {
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

        updateUser(user);
    }

    public List<TrainerProfileDTO> getTrainersProfile() {
        List<User> userTrainers = userRepository.findAllByRole(RoleType.TRAINER);

        return userTrainers.stream()
                .map(u -> trainerProfileDTOMapper.apply(u))
                .collect(Collectors.toList());
    }

    public List<StudentProfileDTO> getStudentsProfile() {
        List<User> userStudents = userRepository.findAllByRole(RoleType.STUDENT);

        return userStudents.stream()
                .map(u -> studentProfileDTOMapper.apply(u))
                .collect(Collectors.toList());
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
