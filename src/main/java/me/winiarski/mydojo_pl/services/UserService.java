package me.winiarski.mydojo_pl.services;

import me.winiarski.mydojo_pl.entities.User;
import me.winiarski.mydojo_pl.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

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
}
