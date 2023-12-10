package me.winiarski.mydojo_pl.app.services;

import me.winiarski.mydojo_pl.app.entities.User;
import me.winiarski.mydojo_pl.app.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public User getUserByEmail(String email){
        final String USER_WITH_E_MAIL_NOT_FOUND = "User with e-mail %s not found";

        return userRepository.findUserByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_WITH_E_MAIL_NOT_FOUND,email)));
        //TODO exception handling
    }

    public void addNewUser(User user) {

        Optional<User> userByEmail =
                userRepository.findUserByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new IllegalStateException("User with provided e-mail already exists.");
        }
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        boolean exists = userRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Student with provided ID: " + id + " does not exists.");
        }
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        Long id = user.getId();
        boolean exists = userRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Student with provided ID: " + id + " does not exists.");
        }
        userRepository.save(user);
    }


//------------------SPRING SECURITY------------------\\

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getUserByEmail(email);
    }
}
