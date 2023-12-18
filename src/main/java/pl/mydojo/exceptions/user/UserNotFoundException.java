package pl.mydojo.exceptions.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User with provided ID: " + id + " does not exist.");
    }

    public UserNotFoundException(String email) {
        super("User with provided e-mail: " + email + " does not exist.");
    }
}
