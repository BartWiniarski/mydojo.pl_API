package pl.mydojo.app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mydojo.app.dto.UserProfileDTO;
import pl.mydojo.app.dto.UserProfileDTOMapper;
import pl.mydojo.app.entities.Role;
import pl.mydojo.app.entities.User;
import pl.mydojo.app.repositories.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserProfileDTOMapper userProfileDTOMapper;

    public UserService(UserRepository userRepository,
                       UserProfileDTOMapper userProfileDTOMapper) {
        this.userRepository = userRepository;
        this.userProfileDTOMapper = userProfileDTOMapper;
    }

//------------------ CRUD ------------------\\
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


//------------------ PROFILE DTO ------------------\\
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
